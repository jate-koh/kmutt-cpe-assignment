/*
 * File:   main.c
 * Author: jatekoh
 *
 * Created on April 27, 2023, 2:38 PM
 */
#define F_CPU 8000000L

#include <iom328p.h>
#include <stdint.h>
#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>

/* SPI Pins */
#define SS PB2
#define MISO PB3
#define SCK PB5

/* LCD Pins */
#define RS PD0
#define E PD1
#define D4 PC0
#define D5 PC1
#define D6 PC2
#define D7 PC3

/* Functions Prototypes */
// Main Functions
void setup();
void loop();

// Init Functions
void init_io();
void init_lcd();
void init_spi();

// LCD Functions
void commit_data();
void clear_lcd();
void send_lcd_command(uint8_t command);
void send_lcd_data(uint8_t data);
void display_lcd_string(char *str);

// Global Variables
volatile float last = 0;
volatile char buffer[100];
volatile char header_str_line[100] = "Temp : ";
volatile char end_str_line[100] = " C";

int main(void) {

    setup();
    while(1){
        loop();
    }

    return 0;
}

// Main Functions
void setup() {
    // Init IO
    init_io();

    // Init LCD
    init_lcd();

    // Init SPI
    init_spi();

    // Display OK
    char str[100] = "OK";
    display_lcd_string(str);
    _delay_ms(1000);
    clear_lcd();
}

void loop() {

    // Clear LCD and display header
    clear_lcd();
    display_lcd_string(header_str_line);

    // Clear SS, in order to start the transmission
    PORTB &= ~(1 << SS);

    // Set SPDR to 0
    SPDR = 0;
    
    // Wait for SPIF to be set
    while (!(SPSR & (1 << SPIF)));

    // Read HByte
    uint8_t HByte = SPDR;

    // Set SPIF to 1 to clear it
    SPSR |= (1 << SPIF);

    // Set SPDR to 0
    SPDR = 0;

    // Wait for SPIF to be set
    while (!(SPSR & (1 << SPIF)));

    // Read LByte
    uint8_t LByte = SPDR;

    // Set SPIF to 1 to clear it
    SPSR |= (1 << SPIF);

    // Set SS to high, in order to end the transmission
    PORTB |= (1 << SS);

    // Combine HByte and LByte
    uint16_t ADCValue = (((HByte & 0x1F) << 8) | LByte) >> 1;
    
    // Calculate Temperature
    float temp = (ADCValue * 500.0) / 4095.0 - 50.0;

    if (temp != last) {
        // Display Temperature
        sprintf(buffer, "%.2f", temp);
        display_lcd_string(buffer);
        display_lcd_string(end_str_line);

        // Update last to latest temperature
        last = temp;
        
        // Wait half a second
        _delay_ms(500);
    }
}

// Init Functions
void init_io() {
    // 'Empty'
}

void init_lcd() {
    // Set RS and E as output
    DDRD |= (1 << RS) | (1 << E);

    // Set D4, D5, D6, D7 as output
    DDRC |= (1 << D4) | (1 << D5) | (1 << D6) | (1 << D7);

    // Set RS and E to low
    PORTD &= ~((1 << RS) | (1 << E));

    // Clear D4, D5, D6, D7
    PORTC &= ~((1 << D4) | (1 << D5) | (1 << D6) | (1 << D7));

    // Send LCD Init Commands
    send_lcd_command(0x33); // Command 0x33 for 4-bit mode
    send_lcd_command(0x32); // Command 0x32 for 4-bit mode
    send_lcd_command(0x28); // 2 Lines & 5x7 matrix
    send_lcd_command(0x0E); // Display On, Cursor Blinking
    send_lcd_command(0x01); // Clear Display
    send_lcd_command(0x80); // Move Cursor to 1,1
}

void init_spi() {
    // Set SS and SCK as output
    DDRB |= (1 << SS) | (1 << SCK);
    
    // Set SS to high
    PORTB |= (1 << SS);

    // Enable SPI, Set as Master
    SPCR |= (1 << SPE) | (1 << MSTR);

    // Reduce Clock Frequency with 8 Prescaler
    SPCR |= (1 << SPR0);
    SPSR |= (1 << SPI2X);

    // Set SPDR to 0
    SPDR = 0;
}

// LCD Functions
void display_lcd_string(char *str) {
    while (*str)
        send_lcd_data(*str++);
}

void clear_lcd() {
    send_lcd_command(0x01); // Clear Display
    send_lcd_command(0x80); // Move Cursor to 1,1
}

void send_lcd_command(uint8_t command) {
    // Set RS to low
    PORTD &= ~(1 << RS);

    // Clear PORTC
    PORTC &= 0x00;
    
    // Send high nibble
    PORTC |= command >> 4;

    // Commit data
    commit_data();

    // Clear PORTC
    PORTC &= 0x00;

    // Send low nibble
    PORTC |= command & 0x0F;

    // Commit data
    commit_data();
}

void send_lcd_data(uint8_t data) {
    // Set RS to high
    PORTD |= (1 << RS);

    // Clear PORTC
    PORTC &= 0x00;

    // Send high nibble
    PORTC |= data >> 4;

    // Commit data
    commit_data();

    // Clear PORTC
    PORTC &= 0x00;

    // Send low nibble
    PORTC |= data & 0x0F;

    // Commit data
    commit_data();
}

void commit_data() {
    // Raise Enable to high
    PORTD |= (1 << E);
    _delay_ms(2);
    // Lower Enable to low
    PORTD &= ~(1 << E);
    _delay_ms(2);
}

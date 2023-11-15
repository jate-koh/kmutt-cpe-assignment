/*
 * File:   lab8-2.c
 * Author: jate-koh
 *
 * Created on April 4, 2021, 08:12 PM
 */
#define F_CPU 8000000L

//#include <iom328p.h>
#include <stdint.h>
#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>

// Define LCD Pins and UltraSonic Pins
#define LCD_D4 PD0
#define LCD_D5 PD1
#define LCD_D6 PD2
#define LCD_D7 PD3
#define LCD_RS PB1
#define LCD_E PB2
#define TRIGGER PC5
#define ECHO PB0

// Global Variables
volatile char buffer[16] = "ahfcfjcjcfh";
volatile uint8_t captureFlag = 0;
volatile uint16_t captureValue = 0;
volatile uint16_t distance = 0;

// LCD Transmission
void commit() {
    // Set E to high  
    PORTB |= (1 << LCD_E);
    _delay_ms(2);

    // Set E to low
    PORTB &= ~(1 << LCD_E);
    _delay_ms(2);
}

void send_data(uint8_t data) {

    // Set RS to high
    PORTB |= (1 << LCD_RS);

    // Clear previous data
    PORTD &= 0x00;

    // Send high nibble
    PORTD |= data >> 4;
    commit();

    // Clear previous data
    PORTD &= 0x00;

    // Send low nibble
    PORTD |= data & 0x0F;
    commit();

}

void send_command(uint8_t command) {

    // Set RS to low
    PORTB &= ~(1 << LCD_RS);

    // Clear previous data
    PORTD &= 0x00;

    // Send high nibble
    PORTD |= command >> 4;
    commit();

    // Clear previous data
    PORTD &= 0x00;

    // Send low nibble
    PORTD |= command & 0x0F;
    commit();

}

// LCD Functions
void init_command() {
    // Set LCD Pins to output
    send_command(0x33);

    // Set 4-bit mode
    send_command(0x32);

    // Set 2 lines
    send_command(0x28);

    // Set cursor to home
    send_command(0x0E);

    // Set cursor to increment
    send_command(0x01);

    // Set cursor to home
    send_command(0x80);
}

// UltraSonic Functions
void display_string(char* str) {

    while (*str != '\0') {
        send_data(*str);
        str++;
    }

}

// Init Functions
void init_lcd() {

    // Set LCD Pins to output
    DDRD |= 0x0F;
    PORTD &= 0xF0;

    // Set RS and E to output
    DDRB |= (1 << LCD_RS) | (1 << LCD_E);
    PORTB &= ~(1 << LCD_RS) & ~(1 << LCD_E);

    // Init LCD
    init_command();
    _delay_ms(1000);

}

void init_ultrasonic() {
    
    // Set Trigger to output
    DDRC |= (1 << TRIGGER);

    // Set Echo to input
    DDRB &= ~(1 << ECHO);
    
}

void init_interrupt() {

    // Set Timer1 to Normal Mode
    TCCR1A = 0x00;
    TCCR1B |= (1 << CS11) | (1 << ICES1) | (1 << ICNC1);
    
    // Enable
    TIMSK1 |= (1 << ICIE1);

}

// Interrupt Service Routine
// For Timer1 Capture Capture Vector
ISR(TIMER1_CAPT_vect) {

    // Check if capture flag is set
    if (captureFlag == 0) {

        // Set Timer1 Counter to falling edge
        TCCR1B &= ~(1 << ICES1);
        // Set capture flag
        captureFlag = 1;
        // Set capture value
        captureValue = ICR1;

    } else {

        // Set Timer1 Counter to rising edge
        TCCR1B |= (1 << ICES1);
        // Clear capture flag
        captureFlag = 0;
        // Calculate distance
        captureValue = ICR1 - captureValue;
        distance = (uint16_t)((captureValue  * 0.034) / 2.0);

    }

}


// Main
int main(void) {
    
    // Init LCD
    init_lcd();

    // Init Ultrasonic
    init_ultrasonic();

    // Init Interrupt
    init_interrupt();

    _delay_ms(1000);

    // Enable Global Interrupts
    sei();

    // Loop Forever
    while (1) {

        // Trigger Ultrasonic
        PORTC |= (1 << TRIGGER);
        _delay_us(10);
        PORTC &= ~(1 << TRIGGER);

        // Clear LCD
        send_command(0x01);
        _delay_ms(2);
        
        // Move Cursor to Home
        send_command(0x80);
        _delay_ms(2);

        // Display Distance
        sprintf(buffer, "Distance: %u cm", distance);
        display_string(buffer);
        _delay_ms(500);

    }

    return 0;
    
}
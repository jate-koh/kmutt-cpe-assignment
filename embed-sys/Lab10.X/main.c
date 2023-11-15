/*
 * File:   main.c
 * Author: jatekoh
 *
 * Created on May 3, 2023, 9:45 AM
 */
#define F_CPU 8000000L

#include <iom328p.h>
#include <stdint.h>
#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>

/* Ports */
#define LCD_DATA PORTD
#define LCD_DATA_DDR DDRD
#define LCD_CTRL PORTB
#define LCD_CTRL_DDR DDRB

/* LCD Pins */
#define RS PB2
#define E PB1
#define D4 PD0
#define D5 PD1
#define D6 PD2
#define D7 PD3

/* Functions Prototypes */
// Main Functions
void setup();
void loop();

// Init Functions
void init_io();
void init_rtc();
void init_i2c();
void init_lcd();

// RTC Functions
void rtc_set_time(uint8_t year, uint8_t month, uint8_t day, uint8_t hour, uint8_t minute, uint8_t second);
void rtc_get_time(uint8_t *year, uint8_t *month, uint8_t *day, uint8_t *hour, uint8_t *minute, uint8_t *second);

// I2C Functions
void i2c_start();
void i2c_stop();
void i2c_write(uint8_t data);
unsigned char i2c_read(uint8_t ack);

// LCD Functions
void commit_data();
void clear_lcd();
void send_lcd_command(uint8_t command);
void send_lcd_data(uint8_t data);
void display_lcd_string(char *str);

// Misc Functions
void display_month(uint8_t month);

// Global Variables
volatile char buffer[100];
volatile uint8_t year, month, day, hour, minute, second;
volatile char month_str;

int main(void) {
        setup();
        while(1){
            loop();
        }
    
        return 0;
}

// Main Functions
void setup() {
    // Init Functions
    init_io();
    init_i2c();
    init_rtc();
    init_lcd();

    // Set Time Manually
    // rtc_set_time(0x23, 0x05, 0x05, 0x17, 0x57, 0x45);

    // Display OK
    char str[10] = "OK";
    display_lcd_string(str);
    _delay_ms(1000);
    clear_lcd();
    
}

void loop() {
    // Clear LCD Screen
    clear_lcd();

    // Get Time
    rtc_get_time(&year, &month, &day, &hour, &minute, &second);

    // Convert to Date String
    // Convert Day String
    sprintf(buffer, "%02x ", day);

    // Display Day String
    display_lcd_string(buffer);

    // Convert Month String
    display_month(month);

    // Convert Year String
    sprintf(buffer, " 20%02x", year);

    // Display Year String
    display_lcd_string(buffer);

    // Create New Line with Command
    send_lcd_command(0xC0);

    // Convert to Time String
    sprintf(
        buffer, 
        "%02x:%02x:%02x", 
        hour, minute, second
    );

    // Display String
    display_lcd_string(buffer);

    // Wait 1 Second
    _delay_ms(1000);
}

// Misc Functions
void display_month(uint8_t month) {
    char str[10];
    switch (month) {
        case 0x01:
            sprintf(str, "Jan");
            display_lcd_string(str);
            break;
        case 0x02:
            sprintf(str, "Feb");
            display_lcd_string(str);
            break;
        case 0x03:
            sprintf(str, "Mar");
            display_lcd_string(str);
            break;
        case 0x04:
            sprintf(str, "Apr");
            display_lcd_string(str);
            break;
        case 0x05:
            sprintf(str, "May");
            display_lcd_string(str);
            break;
        case 0x06:
            sprintf(str, "Jun");
            display_lcd_string(str);
            break;
        case 0x07:
            sprintf(str, "Jul");
            display_lcd_string(str);
            break;
        case 0x08:
            sprintf(str, "Aug");
            display_lcd_string(str);
            break;
        case 0x09:
            sprintf(str, "Sep");
            display_lcd_string(str);
            break;
        case 0x10:
            sprintf(str, "Oct");
            display_lcd_string(str);
            break;
        case 0x11:
            sprintf(str, "Nov");
            display_lcd_string(str);
            break;
        case 0x12:
            sprintf(str, "Dec");
            display_lcd_string(str);
            break;
        default:
            sprintf(str, "Err");
            display_lcd_string(str);
            break;
    }
}

// Init Functions
void init_io() {
    /* Empty */
}

void init_i2c() {
    // Set Prescaler to 1
    TWSR &= ~((1 << TWPS1) | (1 << TWPS0));

    // Set baud rate to 400kHz
    TWBR = 0x48;

    // Enable TWI
    TWCR |= (1 << TWEN);
}

void init_rtc() {
    // Start I2C
    i2c_start();

    // Write control byte
    i2c_write(0xD0);
    i2c_write(0x07);
    i2c_write(0x00);

    // Stop I2C
    i2c_stop();
}

void init_lcd() {
    // Set LCD Data Port to output
    LCD_DATA_DDR |= (1 << D4) | (1 << D5) | (1 << D6) | (1 << D7);

    // Set LCD Control Port to output
    LCD_CTRL_DDR |= (1 << RS) | (1 << E);

    // Set RS and E to low
    LCD_CTRL &= ~((1 << RS) | (1 << E));

    // Clear LCD Data Port
    LCD_DATA &= 0x00;

    // Send Initialization Commands
    send_lcd_command(0x33); // Command 0x33 for 4-bit mode
    send_lcd_command(0x32); // Command 0x32 for 4-bit mode
    send_lcd_command(0x28); // 2 Lines & 5x7 matrix
    send_lcd_command(0x0E); // Display On, Cursor Blinking
    send_lcd_command(0x01); // Clear Display
    send_lcd_command(0x80); // Move Cursor to 1,1
}

// I2C Functions
void i2c_start() {
    // Send Start Condition
    TWCR = (1 << TWINT) | (1 << TWSTA) | (1 << TWEN);

    // Wait for TWINT Flag set
    while (!(TWCR & (1 << TWINT)));
}

void i2c_stop() {
    // Send Stop Condition
    TWCR = (1 << TWINT) | (1 << TWSTO) | (1 << TWEN);
}

void i2c_write(uint8_t data) {
    // Load Data
    TWDR = data;

    // Start Transmission
    // By Clearing TWINT Flag
    TWCR = (1 << TWINT) | (1 << TWEN);

    // Wait for TWINT Flag set
    while (!(TWCR & (1 << TWINT)));
}

unsigned char i2c_read(uint8_t ack) {
    // Clear TWINT Flag to start reading data
    TWCR = (1 << TWINT) | (1 << TWEN) | (ack << TWEA);

    // Wait for TWINT Flag set
    while (!(TWCR & (1 << TWINT)));

    // Return Data
    return TWDR;
}

// RTC Functions
void rtc_set_time(uint8_t year, uint8_t month, uint8_t day, uint8_t hour, uint8_t minute, uint8_t second) {
    // Start I2C
    i2c_start();

    // Write control byte
    i2c_write(0xD0);

    // Write memory address
    i2c_write(0x00);

    // Write time data
    i2c_write(second);
    i2c_write(minute);
    i2c_write(hour);

    // Stop I2C
    i2c_stop();

    // Start I2C
    i2c_start();

    // Write control byte
    i2c_write(0xD0);

    // Write memory address
    i2c_write(0x04);

    // Write date data
    i2c_write(day);
    i2c_write(month);
    i2c_write(year);

    // Stop I2C
    i2c_stop();
}

void rtc_get_time(uint8_t *year, uint8_t *month, uint8_t *day, uint8_t *hour, uint8_t *minute, uint8_t *second) {
    // Send Write Control Byte First to Set Memory Address
    // Start I2C
    i2c_start();

    // Write control byte
    i2c_write(0xD0); // 0xD0 for write

    // Write memory address
    i2c_write(0x00);

    // Stop I2C
    i2c_stop();

    // After Write Control Byte, Send Read Control Byte to Read Data
    // Start I2C
    i2c_start();

    // Write control byte
    i2c_write(0xD1); // 0xD1 for read

    // Read time data
    *second = i2c_read(1); // Send 1 as ACK thru SDA
    *minute = i2c_read(1); // Send 1 as ACK thru SDA
    *hour = i2c_read(0); // Send 0 as NACK thru SDA

    // Stop I2C
    i2c_stop();

    // Send Write Control Byte First to Set Memory Address
    // Start I2C
    i2c_start();

    // Write control byte
    i2c_write(0xD0); // 0xD0 for write

    // Write memory address
    i2c_write(0x04);

    // Stop I2C
    i2c_stop();

    // After Write Control Byte, Send Read Control Byte to Read Data
    // Start I2C
    i2c_start();

    // Write control byte
    i2c_write(0xD1); // 0xD1 for read

    // Read date data
    *day = i2c_read(1); // Send 1 as ACK thru SDA
    *month = i2c_read(1); // Send 1 as ACK thru SDA
    *year = i2c_read(0); // Send 0 as NACK thru SDA

    // Stop I2C
    i2c_stop();
}

// LCD Functions
void display_lcd_string(char *str) {
    while (*str != '\0') {
        send_lcd_data(*str++);
    }
}

void clear_lcd() {
    send_lcd_command(0x01); // Clear Display
    send_lcd_command(0x80); // Move cursor to beginning of first line
    _delay_ms(2);
}

void send_lcd_command(uint8_t command) {
    // Set RS to low
    LCD_CTRL &= ~(1 << RS);

    // Clear LCD Data Port
    LCD_DATA &= 0x00;

    // Send higher nibble
    LCD_DATA |= command >> 4;

    // Commit Data
    commit_data();

    // Clear LCD Data Port
    LCD_DATA &= 0x00;

    // Send lower nibble
    LCD_DATA |= command & 0x0F;

    // Commit Data
    commit_data();
}

void send_lcd_data(uint8_t data) {
    // Set RS to high
    LCD_CTRL |= (1 << RS);

    // Clear LCD Data Port
    LCD_DATA &= 0x00;

    // Send higher nibble
    LCD_DATA |= data >> 4;

    // Commit Data
    commit_data();

    // Clear LCD Data Port
    LCD_DATA &= 0x00;

    // Send lower nibble
    LCD_DATA |= data & 0x0F;

    // Commit Data
    commit_data();
}

void commit_data() {
    // Set E to high
    LCD_CTRL |= (1 << E);

    // Wait 1ms
    _delay_ms(1);

    // Set E to low
    LCD_CTRL &= ~(1 << E);

    // Wait 2ms
    _delay_ms(2);
}
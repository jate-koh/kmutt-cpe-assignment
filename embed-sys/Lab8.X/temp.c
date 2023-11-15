// Lab 8-1
#define F_CPU 8000000L

#include <iom328p.h>
#include <stdint.h>
#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>

// Define LCD Pins
#define LCD_D4 PD0
#define LCD_D5 PD1
#define LCD_D6 PD2
#define LCD_D7 PD3
#define LCD_RS PC5
#define LCD_EN PC4

// Define LED Pins
#define YELLOW_SIGNAL PD6
#define RED_SIGNAL PD7
#define LED_PIN PB1

// Define Potentiometer Pin
#define POT_PIN PC0

// Duty Cycle Mapping
uint8_t map(uint16_t x, uint16_t in_min, uint16_t in_max, uint16_t out_min, uint16_t out_max) {
  return (uint8_t)((x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min);
}

// LCD Functions
void commit() {
  PORTC |= (1 << LCD_EN);
  _delay_ms(1);
  PORTC &= ~(1 << LCD_EN);
  _delay_ms(1);
}

void send_command(uint8_t command) {

  // Set RS to low
  PORTC &= ~(1 << LCD_RS);

  // Clear previous data
  PORTD &= ~(1 << LCD_D4) & ~(1 << LCD_D5) & ~(1 << LCD_D6) & ~(1 << LCD_D7);

  // Send high nibble
  PORTD |= command >> 4;
  commit();

  // Clear previous data
  PORTD &= ~(1 << LCD_D4) & ~(1 << LCD_D5) & ~(1 << LCD_D6) & ~(1 << LCD_D7);

  // Send low nibble
  PORTD |= command & 0x0F;
  commit();

}

void send_data(uint8_t data) {

  // Set RS to high
  PORTC |= (1 << LCD_RS);

  // Clear previous data
  PORTD &= ~(1 << LCD_D4) & ~(1 << LCD_D5) & ~(1 << LCD_D6) & ~(1 << LCD_D7);

  // Send high nibble
  PORTD |= data >> 4;
  commit();

  // Clear previous data
  PORTD &= ~(1 << LCD_D4) & ~(1 << LCD_D5) & ~(1 << LCD_D6) & ~(1 << LCD_D7);

  // Send low nibble
  PORTD |= data & 0x0F;
  commit();

}

void init_commands() {
  // Set 4-bit mode
  send_command(0x33);

  // Set 2 lines
  send_command(0x32);

  // Set cursor to home
  send_command(0x02);

  // Set cursor to increment
  send_command(0x06);

  // Set display to on
  send_command(0x0C);

  // Clear display
  send_command(0x01);
}

void clear_display() {
  send_command(0x01);
}

void display_string(char *string) {
  while (*string != '\0') {
    send_data(*string);
    string++;
  }
}

// Init Functions
void init_io() {
  // Set the LED Signal pin as an output
  DDRD |= (1 << RED_SIGNAL);

  // Set the LED pin as an output
  DDRB |= (1 << LED_PIN);

  // Set the potentiometer pin as an input
  DDRC &= ~(1 << POT_PIN);

  // Enable pull-up resistor on the potentiometer pin
  PORTC |= (1 << POT_PIN);
}

void init_lcd() {
  // Set the LCD pins as outputs
  DDRD |= (1 << LCD_D4) | (1 << LCD_D5) | (1 << LCD_D6) | (1 << LCD_D7);

  // Set the LCD control pins as outputs
  DDRC |= (1 << LCD_RS) | (1 << LCD_EN);

  // Set the LCD control pins to low
  PORTC &= ~(1 << LCD_RS) & ~(1 << LCD_EN);

  // Initialize the LCD
  init_commands();
}

void init_timer() {
  // Set Timer 1 to fast PWM mode, non-inverted output
  TCCR1A |= (1 << WGM10) | (1 << WGM11) | (1 << COM1A1);
  TCCR1B |= (1 << WGM12) | (1 << WGM13);

  // Set the PWM frequency to 490.2 Hz
  TCCR1B |= (1 << CS11) | (1 << CS10); // prescaler of 64

  // Enable Timer 1 overflow interrupt
  TIMSK1 |= (1 << TOIE1);
}

void init_adc() {
  // set ADC Single ended input ADC0
  ADMUX |= 0x0000;
  // set reference voltage to AVcc
  ADMUX |= (1 << REFS0);
  // Enable ADC with prescaler 128
  ADCSRA |= (1 << ADEN) | (1 << ADPS2) | (1 << ADPS1) | (1 << ADPS0);
  // Enable ADC Interrupt
  ADCSRA |= (1 << ADIE);
}

// Loop Operations
void loop() {
  
  // start conversion
  ADCSRA |= (1 << ADSC);
  
  // wait for ADIF
  while (!(ADCSRA & (1 << ADIF)));

  // Set Signal Pin
  PORTD ^= (1 << RED_SIGNAL);
  _delay_ms(100);
  
  // store value ADC
  uint16_t adcValue = ADC;
  //Serial.println(adcValue);
  
  // Map the potentiometer value to a PWM duty cycle (0-255)
  // uint8_t dutyCycle = map(adcValue, 0, 1023, 0, 255);
  uint8_t dutyCycle = adcValue >> 2;
  //Serial.println(dutyCycle);
  
  // Set the PWM duty cycle
  OCR1A = dutyCycle;

  // Delay to allow the LED brightness to settle
  _delay_ms(10);
}

// Interrupts Service Routine
ISR(TIMER1_OVF_vect) {
  // Interrupt handler for Timer 1 overflow
  // This interrupt is used to adjust the PWM frequency (optional)

  // Set the PWM frequency to 490.2 Hz
  OCR1A = 0x03FF; // TOP value for fast PWM mode with a prescaler of 64
}



int main() {

  // Initialize the microcontroller
  init_io();
  init_lcd();
  display_string("LCD IO OK");
  _delay_ms(1000);
  clear_display();

  init_timer();
  display_string("Timer OK");
  _delay_ms(1000);
  clear_display();

  init_adc();
  display_string("ADC OK");
  _delay_ms(1000);
  clear_display();

  //Serial.begin(9600);
  
  // Main loop
  while (1) {
    loop();
  }

  return 0;
}

// Lab 8-2
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
        distance = (uint16_t)((captureValue * 0.034) / 2.0);

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
        sprintf(buffer, "%u", distance);
        display_string(buffer);
        _delay_ms(200);

    }

    return 0;
    
}
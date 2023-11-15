/*
 * File:   lab8-1.c
 * Author: jate-koh
 *
 * Created on April 1, 2021, 01:12 PM
 */
#define F_CPU 8000000L

// #include <iom328p.h>
#include <stdint.h>
#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>

// Define LED Pins
#define YELLOW_SIGNAL PD6
#define RED_SIGNAL PD7
#define LED_PIN PB1

// Define Potentiometer Pin
#define POT_PIN PC5

// Global Variables
volatile uint16_t dutyCycle = 0;
volatile uint16_t adcValue = 0;

int map(uint16_t x, uint16_t in_min, uint16_t in_max, uint16_t out_min, uint16_t out_max) {
  return (int)((x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min);
}

// Init Functions
void init_adc() {

  // Set ADC Channel to ADC4
  ADMUX |= ( 1 << MUX2 );
  
  // Set ADC to use AVCC as reference
  ADMUX |= ( 1 << REFS0 );

  // Set Free Running Mode
  ADCSRA |= ( 1 << ADATE );

  // Set ADC Prescaler to 128
  ADCSRA |= ( 1 << ADPS2 ) | ( 1 << ADPS1 ) | ( 1 << ADPS0 );

  // Enable ADC and its Interrupt
  ADCSRA |= ( 1 << ADEN ) | ( 1 << ADIE );

  // Set Another ADC Register to 0
  ADCSRB = 0x0000;

}

void init_io() {

  // Set LED Pin to Output
  DDRB |= (1 << LED_PIN);

  // Set Red LED Pin to Output
  DDRD |= (1 << RED_SIGNAL);

  // Set Potentiometer Pin to Input
  DDRC &= ~(1 << POT_PIN);

  // // Enable Pull-Up Resistor for Potentiometer Pin
  // PORTC |= (1 << POT_PIN);

  // Clear LED Pin
  PORTB = 0x0000;
  PORTD = 0x0000;

}

void init_timer() {

    // Set Timer 1 to Fast PWM Mode 9-bit
    TCCR1A |= (1 << WGM11) | (1 << COM1A1);
    TCCR1B |=  (1 << WGM12);

    // Set Timer 1 Prescaler to 64
    TCCR1B |= (1 << CS11) | (1 << CS10);

    // Set Noise Canceler
    TCCR1B |= (1 << ICNC1);

    // Set Initial Duty Cycle to 255;
    OCR1A = 255 ;

}

// Interrupt Service Routine
ISR(ADC_vect) {

  // Read ADC Value
  adcValue = ADC;

  // // Blink Red LED Upon Interrupts
  // PORTD ^= (1 << RED_SIGNAL);
  // _delay_ms(1000);

}

int main(void) {

  init_io();
  init_adc();
  init_timer();

  _delay_ms(1000);
  sei();

  while (1) {

    // Start ADC Conversion
    ADCSRA |= (1 << ADSC);

    // Set Duty Cycle
    dutyCycle = adcValue/4;

    // Blink Red LED if ADC Value is Greater than 600 
    if (adcValue > 600) {
      PORTD ^= (1 << RED_SIGNAL);
    }
    else {
      PORTD &= ~(1 << RED_SIGNAL);
    }

    OCR1A = dutyCycle;
    _delay_ms(100);
  }
  return 0;
}
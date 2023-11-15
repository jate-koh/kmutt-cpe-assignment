/*
 * File:   LAB8_1.c
 * Author: tawan
 *
 * Created on March 31, 2023, 11:27 AM
 */
#define F_CPU 8000000L
#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/delay.h>
volatile uint16_t adcValue = 0;
void initADC(){
    ADMUX |= _BV(REFS0);
    ADCSRA |= _BV(ADEN) | _BV(ADATE) | _BV(ADIE) | _BV(ADPS2) | _BV(ADPS1) | _BV(ADPS0);
    ADCSRB = 0;
}

void initPWM(){
    TCCR0A = 0b10000001;
    TCCR0B = 0b00000100;
  	OCR0A = 255;
}

ISR(ADC_vect){
    adcValue = ADC;
}

int main(void){
    initADC();
    initPWM();
    DDRD |= _BV(PD6);
    _delay_ms(1000);
    sei();

    ADCSRA |= _BV(ADSC);
    
    while(1){
        OCR0A = (adcValue/4);
    }
}
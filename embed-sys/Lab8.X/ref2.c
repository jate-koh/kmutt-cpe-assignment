#define F_CPU 8000000L
#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/delay.h>
#define RS PB1
#define E PB2
#define DB4 PD0
#define DB5 PD1
#define DB6 PD2
#define DB7 PD3
#define trigger PC5
#define echo PB0
char buffer[16] = "ahfcfjcjcfh";
volatile uint8_t captureFlag = 0;
volatile uint16_t captureValue = 0;
volatile uint16_t distance = 0;
void initLCD()
{
    DDRD |= 0x0F;
    PORTD &= 0xF0;
    
    DDRB |= (1 << RS) | (1 << E);
    PORTB &= ~(1 << RS) & ~(1 << E);
    
    sendLCDCommand(0x33);
    sendLCDCommand(0x32);
    sendLCDCommand(0x28);
    sendLCDCommand(0x0E);
    sendLCDCommand(0x01);
    sendLCDCommand(0x80);
}

void sendLCDCommand(uint8_t command)
{
    PORTB &= ~(1 << RS);
    PORTD &= 0x00;
    PORTD = command >> 4;
    
    commitData();
    
    PORTD &= 0x00;
    PORTD |= command & 0x0F;
    
    commitData();
}

void commitData()
{
    PORTB |= (1 << E);
    _delay_ms(2);
    PORTB &= ~(1 << E);
}

void displayString(char* str)
{
    while(*str != '\0')
    {
        sendLCDData(*str);
        str++;
    }
}

void sendLCDData(uint8_t command)
{
    PORTB |= (1 << RS);
    PORTD &= 0x00;
    PORTD = command >> 4;
    
    commitData();
    
    PORTD &= 0x00;
    PORTD |= command & 0x0F;
    
    commitData();
}

void ultraSonicSetup(){
    DDRC |= _BV(trigger);
    DDRC &= ~_BV(echo);
}

void initInterrupt(){
    TCCR1A = 0x00;
    TCCR1B = _BV(CS11) | _BV(ICES1) | _BV(ICNC1);
    TIMSK1 = _BV(ICIE1);
}

ISR(TIMER1_CAPT_vect){
    if(captureFlag == 0)
    {
        TCCR1B &= ~(1<<ICES1);
        captureFlag = 1;
        captureValue = ICR1;
    }
    else
    {
        TCCR1B |= (1<<ICES1);
        captureFlag = 0;
        captureValue = ICR1 - captureValue;
        distance = (uint16_t)((captureValue * 0.034) / 2.0);
    }
}

int main(void){
    initLCD();
    ultraSonicSetup();
    initInterrupt();
    _delay_ms(1000);

    sei();
    while(1){
        PORTC |= _BV(trigger);
        _delay_us(10);
        PORTC &= ~_BV(trigger);
        sendLCDCommand(0x01);
        _delay_ms(2);
        sendLCDCommand(0x80);
        sprintf(buffer, "%u", distance);
        displayString(buffer);
        _delay_ms(200);
    }
}
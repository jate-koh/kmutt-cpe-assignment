#define F_CPU 8000000L

#include <avr/io.h>
#include <util/delay.h>
#include <stdio.h>

#define RS PD0
#define E PD1
#define D4 PC0
#define D5 PC1
#define D6 PC2
#define D7 PC3

char headerStrLine[100] = "Temp : ";

void commitData();
void sendLCDCommand(uint8_t command);
void initLCD();

void sendLCDData(uint8_t command);
void displayLCDString(char *str);

int main(void)
{
    initLCD();

    DDRB |= (1 << DDB2) | (1 << DDB5);
    PORTB |= (1 << PORTB2);

    SPCR |= (1 << SPE) | (1 << MSTR) | (1 << SPR0);
    SPSR |= (1 << SPI2X);

    char buffer[100];

    float last = 0;

    sendLCDCommand(0x01);
    sendLCDCommand(0x0C);
    sendLCDCommand(0x80);
    displayLCDString(headerStrLine);


    while (1)
    {
        PORTB &= ~(1 << PORTB2);

        SPDR = 0;
        while (!(SPSR & (1 << SPIF)))
            ;
        uint8_t HByte = SPDR;
        SPSR |= (1 << SPIF);

        SPDR = 0;
        while (!(SPSR & (1 << SPIF)));
        uint8_t LByte = SPDR;
        SPSR |= (1 << SPIF);

        PORTB |= (1 << PORTB2);

        uint16_t ADCValue = (((HByte & 0x1F) << 8) | LByte) >> 1;

        float temp = (ADCValue * 500.0) / 4095.0 - 50.0;

        if (temp != last)
        {
            sprintf(buffer, "%.2fC", temp);
            displayLCDString(buffer);

            last = temp;
            
            sendLCDCommand(0x10);
            sendLCDCommand(0x10);
            sendLCDCommand(0x10);
            sendLCDCommand(0x10);
            sendLCDCommand(0x10);
            sendLCDCommand(0x10);

            _delay_ms(500);
        }
    }
}

void commitData()
{
    PORTD |= (1 << E);
    _delay_ms(2);
    PORTD &= ~(1 << E);
    _delay_ms(2);
}

void sendLCDCommand(uint8_t command)
{
    PORTD &= ~(1 << RS);
    PORTC &= 0x00;
    PORTC = command >> 4;

    commitData();

    PORTC &= 0x00;
    PORTC |= command & 0x0F;

    commitData();
}

void initLCD()
{
    DDRC |= 0x0F;
    PORTC &= 0xF0;

    DDRD |= (1 << RS) | (1 << E);
    PORTD &= ~(1 << RS) & ~(1 << E);

    sendLCDCommand(0x33);
    sendLCDCommand(0x32);
    sendLCDCommand(0x28);
    sendLCDCommand(0x0E);
    sendLCDCommand(0x01);
    sendLCDCommand(0x80);
}

void sendLCDData(uint8_t command)
{
    PORTD |= (1 << RS);
    PORTC &= 0x00;
    PORTC = command >> 4;

    commitData();

    PORTC &= 0x00;
    PORTC |= command & 0x0F;

    commitData();
}

void displayLCDString(char *str)
{
    while (*str)
    {
        sendLCDData(*str);
        str++;
    }
}
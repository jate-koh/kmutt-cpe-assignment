#include <stdint.h>
#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>
#include <stdio.h>

#define F_CPU 8000000UL

void i2c_init(void);
{
  TWSR &= ~((1 << TWPS1) | (1 << TWPS0)); // Set prescaler bits for 1
  TWBR = 0x48; // Set bit rate register
  TWCR |= (1 << TWEN); // Enable TWI
}

void i2c_start(void);
{
  TWCR = (1 << TWINT) | (1 << TWSTA) | (1 << TWEN); // Send start condition
  while (!(TWCR & (1 << TWINT))); // Wait for TWINT flag set
}

void i2c_stop(void);
{
  TWCR = (1 << TWINT) | (1 << TWSTO) | (1 << TWEN); // Send stop condition
}

void i2c_write(uint8_t data);
{
  TWDR = data; // Load data register with data
  TWCR = (1 << TWINT) | (1 << TWEN); // Clear TWINT bit in TWCR to start transmission of data
  while (!(TWCR & (1 << TWINT))); // Wait for TWINT flag set
}

unsigned char i2c_read(unsigned char ack);
{
  TWCR = (1 << TWINT) | (1 << TWEN) | (ack << TWEA); // Clear TWINT bit in TWCR to start transmission of data
  while (!(TWCR & (1 << TWINT))); // Wait for TWINT flag set
  return TWDR; // Return received data from TWDR
}

void rtc_init(void)
{
  i2c_init();
  i2c_start();
  i2c_write(0xD0);
  i2c_write(0x07);
  i2c_write(0x00);
  i2c_stop();
}

void rtc_setTime(uint8_t year, uint8_t month, uint8_t day, uint8_t hour, uint8_t minute, uint8_t second)
{
  // Set the seconds, minutes, and hours on the RTC module
  i2c_start();
  i2c_write(0xD0);
  i2c_write(0x00);
  i2c_write(second);
  i2c_write(minute);
  i2c_write(hour);
  i2c_stop();

  // Set the day, month, and year on the RTC module
  i2c_start();
  i2c_write(0xD0);
  i2c_write(0x04);
  i2c_write(day);
  i2c_write(month);
  i2c_write(year);
  i2c_stop();
}

void rtc_getTime(uint8_t *year, uint8_t *month, uint8_t *day, uint8_t *hour, uint8_t *minute, uint8_t *second)
{
  // Read the seconds, minutes, and hours from the RTC module
  i2c_start();
  i2c_write(0xD0);
  i2c_write(0x00);
  i2c_stop();

  i2c_start();
  i2c_write(0xD1);
  *second = i2c_read(1);
  *minute = i2c_read(1);
  *hour = i2c_read(0);
  i2c_stop();

  // Read the day, month, and year from the RTC module
  i2c_start();
  i2c_write(0xD0);
  i2c_write(0x04);
  i2c_stop();

  i2c_start();
  i2c_write(0xD1);
  *day = i2c_read(1);
  *month = i2c_read(1);
  *year = i2c_read(0);
  i2c_stop();
}

// LCD Functions
void commitData()
{
  PORTB |= (1 << LCD_E);
  _delay_ms(2);
  PORTB &= ~(1 << LCD_E);
  //_delay_us(10);
}

void sendLCDCommand(uint8_t command)
{
  // pull RS low
  PORTB &= ~(1 << LCD_RS);
  
  // send high nibble of the command
  PORTC &= 0xF0; // 0b11110000
  PORTC |= command >> 4;
  commitData();
 
  // send low nibble of the command
  PORTC &= 0xF0; // 0b11110000
  PORTC |= (command & 0x0F);
  commitData();
}

void sendLCDData(uint8_t command)
{
  // pull RS high
  PORTB |= (1 << LCD_RS);
  
  // send high nibble of the command
  PORTC &= 0xF0; // 0b11110000
  PORTC |= command >> 4;
  commitData();
 
  // send low nibble of the command
  PORTC &= 0xF0; // 0b11110000
  PORTC |= (command & 0x0F);
  commitData();
}

void LCDDisplayString(const char* str)
{ 
  // loop to display characters
  while (*str != '\0')
  {
    sendLCDData(*str);
    str++;
  }
}

int main(void)
{
  uint8_t year, month, day, hour, minute, second;
  rtc_init();
  rtc_setTime(0x23, 0x05, 0x03, 0x10, 0x30, 0x00);
  
  while (1)
  {
    rtc_getDateTime(&year, &month, &day, &hour, &minute, &second);
    
    // Convert the hour, minute, and second values to strings
    char yearStr[5], monthStr[12], dayStr[3], hourStr[3], minuteStr[3];
    sprintf(yearStr, "%s", year);
    sprintf(monthStr, "%s", month);
    sprintf(dayStr, "%s", day);
    sprintf(hourStr, "%02d", hour);
    sprintf(minuteStr, "%02d", minute);

    LCDDisplayString(yearStr);
    LCDDisplayString(" ");
    LCDDisplayString(monthStr);
    LCDDisplayString(" ");
    LCDDisplayString(dayStr);
    LCDDisplayString(" ");
    LCDDisplayString(hourStr);
    LCDDisplayString(":");
    LCDDisplayString(minuteStr);

    _delay_ms(1000);
  }
  return 0;
}
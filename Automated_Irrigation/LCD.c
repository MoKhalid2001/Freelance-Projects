/*
 * LCD.c
 *
 *  Created on: Jul 4, 2024
 *      Author: mk225
 */


#include <avr/io.h>
#include<avr/delay.h>

#include "LIB/BIT_MATH.h"
#include "LIB/STD_TYPES.h"

#include "LCD.h"

void LCD_INIT(void){
	 LCD_DATA_PORT_DDR = 0xFF;
	 SET_BIT(LCD_CONTROL_PORT_DDR, RS_PIN);
	 SET_BIT(LCD_CONTROL_PORT_DDR, RW_PIN);
	 SET_BIT(LCD_CONTROL_PORT_DDR, E_PIN);

	 _delay_ms(30);
	 LCD_WRITE_CMD(LCD_SET);
	 _delay_ms(1);
	 LCD_WRITE_CMD(DISPLAY_ON_CONTROL);
	 _delay_ms(1);
	 LCD_WRITE_CMD(CLEAR_DISPLAY);
	 _delay_ms(2);
	 LCD_WRITE_CMD(ENTRY_MODE_SET);
}

void LCD_WRITE_DATA(u8 data){
	/* Set RS to HIGH */
	SET_BIT(LCD_CONTROL_PORT, RS_PIN);
	/* Set R/W to LOW */
	CLR_BIT(LCD_CONTROL_PORT, RW_PIN);
	/* Set E to HIGH */
	SET_BIT(LCD_CONTROL_PORT, E_PIN);

	/*set data*/
	LCD_DATA_PORT = data;
	_delay_ms(5);

	/* Set E to LOW */
	CLR_BIT(LCD_CONTROL_PORT, E_PIN);
	_delay_ms(10);
}

void LCD_WRITE_CMD(u8 command){
	/* Set RS to HIGH */
	CLR_BIT(LCD_CONTROL_PORT, RS_PIN);
	/* Set R/W to LOW */
	CLR_BIT(LCD_CONTROL_PORT, RW_PIN);
	/* Set E to HIGH */
	SET_BIT(LCD_CONTROL_PORT, E_PIN);
	/*set data*/
	LCD_DATA_PORT= command;
	_delay_ms(5);
	/* Set E to LOW */
	CLR_BIT(LCD_CONTROL_PORT, E_PIN);
	/* Delay for 10ms to do command*/
	_delay_ms(10);
}

void LCD_SET_CURSOR_POSITION(u8 row, u8 col){

	u8 command=127;
	if(col>=1&&col<=16){
		switch(row){
		case 1:
			command+=col;
			break;
		case 2:
			command+=col+64;
			break;
		default:break;
		}
		LCD_WRITE_CMD(command);
	} else{

	}
}

void LCD_WRITE_STRING(u8* string){
	 u8 counter=0;
	 for(counter=0;string[counter]!='\0';counter++){
		 LCD_WRITE_DATA(string[counter]);
		 _delay_ms(2);
	 }
}

void LCD_WRITENUMBER(u16 value){
 	if(value==0){
 		LCD_WRITE_DATA(value+48);
 	}
 else{
 	u8 Lastdigait=0;
 	u32 TenPowLastdigait=1;
 	while(value/TenPowLastdigait){
 		Lastdigait++;
 		TenPowLastdigait*=10;
 	}
 	for(u8 iteration=0;iteration<Lastdigait;iteration++){
 		TenPowLastdigait/=10;
 		u8 Number=value/TenPowLastdigait;
 		LCD_WRITE_DATA(Number+48);
 		value=value-Number*TenPowLastdigait;
 	}
 }
 		_delay_ms(2);
 }


#include <SoftwareSerial.h>
SoftwareSerial SerialBt(2,3); //rx tx
int MotorIzquierdoA=4;
int MotorIzquierdoB=5;
int MotorDerechoA=6;
int MotorDerechoB=7;

void Parar();
void Derecha();
void Izquierda();
void Retroceder();
void avanzar();


void setup()  
{

  Serial.begin(9600);
    pinMode(MotorIzquierdoA, OUTPUT);
    pinMode(MotorDerechoA, OUTPUT);
    pinMode(MotorIzquierdoB, OUTPUT);
    pinMode(MotorDerechoB, OUTPUT);

  SerialBt.begin(38400);//PARA CONFIGURACION
  delay(500);
}

void loop() 
{
  char dato;
  if (!SerialBt.available())
    return;
  dato=SerialBt.read();
  Serial.print(dato);
  switch(dato)
  {
    case 'A': //avanzar
      avanzar();
    break;
    case 'R':
      Retroceder();
    break;
    case 'I': //izquierda
      Izquierda();
    break;
    case 'D': //derecho
      Derecha();
    break;
    case 'P': //Parara
      Parar();
    break;
  }
}

void Parar()
{
      digitalWrite(MotorIzquierdoA,LOW);
      digitalWrite(MotorIzquierdoB,LOW);

      digitalWrite(MotorDerechoA,LOW);
      digitalWrite(MotorDerechoB,LOW);
}

void Derecha()
{
      digitalWrite(MotorIzquierdoA,LOW);
      digitalWrite(MotorIzquierdoB,LOW);

      digitalWrite(MotorDerechoA,HIGH);
      digitalWrite(MotorDerechoB,LOW);

}

void Izquierda()
{
      digitalWrite(MotorIzquierdoA,HIGH);
      digitalWrite(MotorIzquierdoB,LOW);

      digitalWrite(MotorDerechoA,LOW);
      digitalWrite(MotorDerechoB,LOW);

}

void Retroceder()
{
      digitalWrite(MotorIzquierdoA,LOW);
      digitalWrite(MotorIzquierdoB,HIGH);

      digitalWrite(MotorDerechoA,LOW);
      digitalWrite(MotorDerechoB,HIGH);
}

void avanzar()
{
      digitalWrite(MotorIzquierdoA,HIGH);
      digitalWrite(MotorIzquierdoB,LOW);

      digitalWrite(MotorDerechoA,HIGH);
      digitalWrite(MotorDerechoB,LOW);
}
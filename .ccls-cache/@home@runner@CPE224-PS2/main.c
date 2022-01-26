#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>

int isNeg(int num);
void Converter(int n);
void SignMagnitude(int n);
void FirstComp(int n);
void SecondComp(int n);
void Print(int ans[],int count);

int main() {
    char str[20];
    gets(str);
    
    int n=0,check=0;
    scanf("%d",&n);
    printf("%d",n);
    check = isNeg(n); //0 = Zero, 1 = Positive, -1 = Negative
    Converter(fabs(n)); 

    //printf("1. Sign-and-Magnitude");


    return 0;
}

int isNeg(int num) { //0 = Zero, 1 = Positive, -1 = Negative
    printf("Enter a number: ");
    scanf("%d", &num);
    if(num<=0) {
        if(num == 0) return 0;
        else return -1;
    }
    else return 1;
}

void Converter(int n) {
    int ans[32], i=0, count=0, r=0, c=0;
    ans[count++] = 0;
    while(n>0) {
        ans[count++] = n%2;
        n = n/2;
    }
    Print(ans,count);
}

void SignMagnitude(int n) {

}

void FirstComp(int n) {

}

void SecondComp(int n) {

}

void Print(int ans[],int count) {
    int i=0;
    printf("\n Print: ");
    for(i=0; i<count; i++)
        printf("%d",ans[i]);
    printf("\n");
}
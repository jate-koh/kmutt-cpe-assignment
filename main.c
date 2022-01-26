#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>

int strCheck(char str[]);
void Converter(int n);
void SignMagnitude(int n);
void FirstComp(int n);
void SecondComp(int n);
void Print(int ans[],int count);

int main() {
    char str[20];
    int check=0;
    gets(str);
    check = strCheck(str); 
    /* -2 = Negative Zero, -1 = Negative,
      0 = Positive Zero, 1 = Positive*/
    
    int num = atoi(str);
    
    printf("0. Pre-conversion : ");
    Converter(num);

    //printf("1. Sign-and-Magnitude");
    
    return 0;
}

int strCheck(char str[]) { 
    int i=0;
    if(str[0]=='-') { //Negative
      for(i=0;i<=strlen(str)-1;i++)
        str[i] = str[i+1];
      if(atoi(str)==0) return -2; //Negative Zero
      else return -1;
    }
    else { //Positive
      if(str[0]=='+')
        for(i=0;i<=strlen(str)-1;i++)
          str[i] = str[i+1];
      if(atoi(str)==0) return 0; //Positivie Zero
      else return 1;
    }
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
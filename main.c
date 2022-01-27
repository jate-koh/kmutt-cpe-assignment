#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>

//Converter Functions
void Converter(int n,int ans[],int count);
void SignMagnitude(int n,int ans[],int check,int count);
void FirstComp(int n,int ans[],int check,int count);
void SecondComp(int n,int ans[],int check,int count);
//Misc. Functions
int strCheck(char str[]);
void init_array(int ans[],int count);
void Print(int ans[],int count);

int ans[32];

int main() {
    char str[20];
    int check=0,count=0;
    printf("Input Decimal Number : ");
    gets(str);
    check = strCheck(str); 
    /* -2 = Negative Zero, -1 = Negative,
      0 = Positive Zero, 1 = Positive*/
    
    printf("Number of bits [More than 4]\n");
    scanf("%d",&count);

    while(count<4) {
      printf("Please enter number more than 4\n");
      count = 0;
      scanf("%d",&count);
    }

    printf("%d-bits array generated",count);
    Print(ans,count);
    
    int num = atoi(str);
    //count = Converter(num,ans,check,count);
    //init_array(ans,count);
    printf("\nI. Sign-and-Magnitude");
    SignMagnitude(num,ans,check,count);
    init_array(ans,count);
    printf("\nII. 1's Complement");
    FirstComp(num,ans,check,count);
    init_array(ans,count);
    printf("\nIII. 2's Complement");
    SecondComp(num,ans,check,count);
    init_array(ans,count);
    
    return 0;
}

void Converter(int n,int ans[],int count) {
    int index = count-1;
    while(n>0) {
        ans[index--] = n%2;
        n = n/2;
    }
}

void SignMagnitude(int n,int ans[],int check,int count) {
    Converter(n,ans,count);
    if(check==-1 || check==-2) ans[0]=1;
    else ans[0]=0;
    Print(ans,count);
}

void FirstComp(int n,int ans[],int check,int count) {
    Converter(n,ans,count);
    if(check==-1 || check==-2) {
      for(int i = 0; i<count; i++) {
          if (ans[i] == 0) ans[i] = 1;
          else ans[i] = 0;
      }
    }
    Print(ans,count);
}

void SecondComp(int n,int ans[],int check,int count) {
    char str[20];
    ans[count++] = 0;
    n -= 1;
    while(n>0) {
        ans[count++] = n%2;
        n = n/2;
    }
    for(int i = 0; i<count; i++) {
        if (ans[i] == 0) ans[i] = 1;
        else ans[i] = 0;
    }
    /* for(int i = 0; i<count ; i++)
    {
      if(ans[count]==1)
    {
        ans[count]=0;
               if(ans[count-1]==1)
                    {
                        ans[count-1]=0;
                        if(ans[count-2]==1)
                            {
                                ans[count-2]=0;
                                if(ans[count-3]==1)
                                {
                                    ans[count-3]=0;
                                    if(ans[count-4]==1)
                                    {
                                        ans[count-4]=0;
                                        ans[count-5]=1;
                                    }
                                    else
                                        ans[count-4]=1;
                                }
                                else
                                    ans[count-3]=1;
                            }
                        else
                            ans[count-2]=1;
                    }
                else
                    ans[count-1]=1;
    }
    else
        ans[count]=1;
    } */
    Print(ans,count);
}

// Misc. Functions
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

void init_array(int ans[],int count) {
  int i=0;
  for(i=0;i<count;i++)
    ans[i]=0;
}

void Print(int ans[],int count) {
    int i=0;
    printf("\n Print: ");
    for(i=0; i<count; i++)
        printf("%d",ans[i]);
    printf("\n");
}
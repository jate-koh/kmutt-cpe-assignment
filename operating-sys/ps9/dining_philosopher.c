#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include<unistd.h>

#define N 5 //Specify Amount of Philosophers

/* Philosopher State */
#define THINKING 2
#define HUNGRY 1
#define EATING 0

/* Fork State */
#define LEFT (ph_num + 1) % N
#define RIGHT ph_num
#define AVAILABLE 1
#define UNAVAILABLE 0

int state[N];
int forkstate[N];
int phil[N] = { 0, 1, 2, 3, 4 }; //Array of Philosophers
 
sem_t mutex;
sem_t S[N];

void eat(int ph_num) {
    state[ph_num] = EATING;
    sleep(2);
    printf("\n**** Philosopher %d is Eating ****\n\n", ph_num+1);
}

void waitRight(int ph_num) {
    printf("Philosopher %d is waiting for right fork %d\n", ph_num+1, RIGHT+1);
    do {
        sem_wait(&S[ph_num]);
    } 
    while(forkstate[RIGHT] == UNAVAILABLE);
    sem_post(&S[ph_num]);
}

void waitLeft(int ph_num) {
    printf("Philosopher %d is waiting for left fork %d\n", ph_num+1, LEFT+1);
    do {
        sem_wait(&S[ph_num]);
    } 
    while(forkstate[LEFT] == UNAVAILABLE);
    sem_post(&S[ph_num]);
}

void getLeftFork(int ph_num) {
    sem_wait(&mutex);
    
    waitLeft(ph_num);

    forkstate[LEFT] = UNAVAILABLE;
    printf("Philosopher %d is picking up left fork %d\n", ph_num+1, LEFT+1 );

    sem_post(&mutex);
}

void getRightFork(int ph_num) {
    sem_wait(&mutex);

    waitRight(ph_num);

    forkstate[RIGHT] = UNAVAILABLE;
    printf("Philosopher %d is picking up right fork %d\n", ph_num+1, RIGHT+1 );

    sem_post(&mutex);
}

void putLeftFork(int ph_num) {
    sem_wait(&mutex);

    printf("Philosopher %d is putting down left fork %d\n", ph_num+1, LEFT+1);
    forkstate[LEFT] = AVAILABLE;

    sem_post(&mutex);
}

void putRightFork(int ph_num) {
    sem_wait(&mutex);

    printf("Philosopher %d is putting down right fork %d\n", ph_num+1, RIGHT+1);
    forkstate[RIGHT] = AVAILABLE;

    sem_post(&mutex);
}

void getFork(int ph_num) {
    state[ph_num] = HUNGRY;
    printf("\n**** Philosopher %d is Hungry ****\n\n", ph_num+1);
    getLeftFork(ph_num);
    getRightFork(ph_num);
}

void putFork(int ph_num) {
    state[ph_num] = THINKING;
    putLeftFork(ph_num);
    putRightFork(ph_num);
    printf("\n**** Philosopher %d is Thinking ****\n\n", ph_num+1);
}

void* philosopher(void* num) {
    int* number = num;
    while(1) {
        sleep(1); //think
        getFork(*number);
        eat(*number);
        putFork(*number);
    }

}

int main() {
    printf("**** Dining Philosopher (No Deadlock) ****\n");
     printf("**** Written by 1005, 1013, 1014, 1016, 1019 ****\n\n");


    int i = 0;

    pthread_t thread_id[N];

    sem_init(&mutex,0,1);

    for (i = 0; i < N; i++) {
        sem_init(&S[i], 0, 0);
        forkstate[i] = AVAILABLE;
    }
        
 
    for (i = 0; i < N; i++) {
        pthread_create(&thread_id[i], NULL, philosopher, &phil[i]);
        printf("\n**** Philosopher %d is Thinking ****\n", i + 1);
    }
 
    for (i = 0; i < N; i++)
        pthread_join(thread_id[i], NULL);

    return 0;

}
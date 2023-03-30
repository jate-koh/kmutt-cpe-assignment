/**
 *  abstractQueue.h
 *
 *  Header file defining the operations that one can
 *  do on a generic or abstract queue. The elements of
 *  the list can be pointers to anything.
 *
 *  This can be implemented in a variety of ways.
 *
 *  Created by Sally Goldin, 21 December 2011 for CPE113
 *
 */

#ifndef ABSTRACTQUEUE_H
#define ABSTRACTQUEUE_H

/**
 * Add a data item to the queue (end of the list)
 * Arguments:
 *   data      -   Pointer to generic data we want to add to queue   
 * Returns 1 if successful, 0 if we have run out of space.
 */
int enqueue(void* data);

/* Get the next item in the queue. This is the element 
 * at the front of the queue.
 * Returns the data stored at the front of the queue.
 * Also removes that item from the queue.
 * Returns NULL if the queue is empty.
 */
void * dequeue();

/* Find out how many items are currently in the queue.
 * Return number of items in the queue (could be zero)
 */
int queueSize();

/* Clear so we can reuse 
 */
void queueClear();


/** DEBUGGING FUNCTION PRINTS SOME INFO ABOUT THE QUEUE **/
void printDebug();

#endif

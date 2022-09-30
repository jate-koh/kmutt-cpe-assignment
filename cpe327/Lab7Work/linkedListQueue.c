/*
 *   linkedListQueue.c 
 *
 *   Linked list implementation of an abstract queue
 *
 *   Created by Sally Goldin, 18 January 2012, for CPE 113
 *
 */

#include <stdlib.h>
#include <stdio.h>
#include "abstractQueue.h"


/* Structure that represents one item of the list */
typedef struct _listitem
{
    void* data;               /* Pointer to the data for this node */
    struct _listitem * next;  /* Link to the next item in the list */
} LISTITEM_T;

static int count = 0;              /* number of items in the queue */
static LISTITEM_T * head = NULL;   /* front item in the queue */
static LISTITEM_T * tail = NULL;   /* end item in the queue */


/**
 * Add a data item to the queue (end of the list)
 * Arguments:
 *   data      -   Pointer to generic data we want to add to queue   
 * Returns 1 if successful, 0 if we have run out of space.
 */
int enqueue(void* data)
{
   int bOk = 1;
   LISTITEM_T * newItem = (LISTITEM_T *)calloc(1,sizeof(LISTITEM_T));
   if (newItem == NULL)
       {
       bOk = 0;
       }
   else
       {
       newItem->data = data;  /* store the data */
       if (head == NULL)       /* nothing in the queue yet */
          {
	  head = newItem;      
          }
       else
          {
	  tail->next = newItem;  /* add to the end of the queue */ 
          }
       tail = newItem;         /* either way the new item is now the end */
       count++;
       }
   return bOk;
}


/* Get the next item in the queue. This is the element 
 * at the front of the queue.
 * Returns the data stored at the front of the queue.
 * Also removes that item from the queue.
 * Returns NULL if the queue is empty.
 */
void * dequeue()
{
   void * returnData = NULL;
   if (count > 0)
      {
      LISTITEM_T * firstItem = head;
      returnData = firstItem->data;
      head = firstItem->next;
      if (head == NULL)  /* if that was the last item on the queue */
	  tail = NULL;
      free(firstItem);
      count--;
      }
   return returnData;
}


/* Find out how many items are currently in the queue.
 * Return number of items in the queue (could be zero)
 */
int queueSize()
{
   return count;
}


/* Clear so we can reuse 
 */
void queueClear()
{
    //int i = 0; /* 63070501013 - Modified 2022-09-28 - Unused variable */
    LISTITEM_T * item = head;
    LISTITEM_T * freeItem = NULL;
    while (item != NULL)
       {
       freeItem = item;
       item = item->next;
       free(freeItem);
       }
    /* reset head, tail and count */
    head = NULL;
    tail = NULL;
    count = 0;
}


/** DEBUGGING FUNCTION PRINTS SOME INFO ABOUT THE QUEUE **/
void printDebug()
{
   //int i,j; /* 63070501013 - Modified 2022-09-28 - Unused variables */
   printf("count = %d   head = %p  tail = %p\n",
	  count, (void*)head, (void*)tail); 
     /* 63070501013 - Modified 2022-09-28 - %p require head 
     and tail to be type of void pointer */
   if (count > 0)
      {
      printf("Contents: \n");
      LISTITEM_T * item = head;
      while (item != NULL)
        {
	  printf("\t\t%s\n", (char*) item->data);
	item = item->next;
	}
      }
}

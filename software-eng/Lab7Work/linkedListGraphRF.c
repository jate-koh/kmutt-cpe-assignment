/*
 *  linkedListGraphRF.c
 *
 *  Implements an abstractGraph using adjacency lists (linked lists).
 *  This is a structure with two levels of list. There is a master
 *  list of vertices, linked in series. Each vertex points to a subsidiary
 *  linked list with references to all the other vertices to which it
 *  is connected.
 *
 *  Key values are strings and are copied when vertices are inserted into
 *  the graph. Every vertex has a void* pointer to additional data which
 *  is simply stored. 
 *
 *  Created by Sally Goldin, 18 January 2012 for CPE 113
 *  Revised to improve names 5 March 2013, 2 March 2017
 *  Revised to fix bug - removeEdge did not return bOk - 11 March 2019
 *  THIS VERSION MODIFIED TO INSERT CODE SMELLS,for CPE327
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "abstractGraph.h"
#include "abstractQueue.h"
#include "graphStruct.h"
/* 63070501013 - Modified 2022-09-29 
- Include graphStruct header, where all type def is placed */

#define WHITE 0
#define GRAY  1
#define BLACK 2

char* colorName[] = {"WHITE", "GRAY", "BLACK"};

/* 63070501013 - Modified 2022-09-29 
- Move struct into graphStruct header, so that all type can be
easily manage from there */

VERTEX_T * vListHead = NULL;  /* head of the vertex list */
VERTEX_T * vListTail = NULL;  /* tail of the vertex list */
int bGraphDirected = 0;       /* if true, this is a directed graph */
int bMaxVertices = 0; /* 63070501013 - Modified 2022-09-28 - Add this variable so that maxVertices 
can be used when running initGraph() */

/* Finds the vertex that holds the passed key
 * (if any) and returns a pointer to that vertex.
 * Arguments
 *       key    -  Key we are looking for
 *       pPred  -  used to return the predecessor if any
 * Returns pointer to the vertex structure if one is found       
 */
VERTEX_T * findVertexByKey(char* key, VERTEX_T** pPred) 
{
    VERTEX_T * pFoundVtx = NULL;
    VERTEX_T * pCurVertex = vListHead;
    *pPred = NULL;
    /* while there are vertices left and we haven't found
     * the one we want.
     */
    while ((pCurVertex != NULL) && (pFoundVtx == NULL))
       {
       if (strcmp(pCurVertex->key,key) == 0)
          {
	  pFoundVtx = pCurVertex;
	  }
       else
          {
	  *pPred = pCurVertex;
          pCurVertex = pCurVertex->next;
          }
       }
    return pFoundVtx;
}

/* Free the adjacencyList for a vertex 
 * Argument
 *   pVertex    - vertex whose edges we want to delete 
 */
void freeAdjacencyList(VERTEX_T *pVertex)
{
   ADJACENT_T * pCurRef = pVertex->adjacentHead;
   while (pCurRef != NULL)
      {
      ADJACENT_T * pDelRef = pCurRef;
      pCurRef = pCurRef->next;
      free(pDelRef);
      }
   pVertex->adjacentHead = NULL;
   pVertex->adjacentTail = NULL;
}

/* 63070501013 - Modified 2022-09-28 - move clearGraph position, 
because it use freeAdjacency function above */

/* Free all memory associated with the graph and
 * reset all parameters.
 */
void clearGraph()
{
    VERTEX_T * pCurVertex = vListHead;
    while (pCurVertex != NULL)
       {
       freeAdjacencyList(pCurVertex);
       VERTEX_T * pDelVtx = pCurVertex;
       pCurVertex = pCurVertex->next;
       free(pDelVtx->key);
       free(pDelVtx);
       }

    vListHead = NULL;  
    vListTail = NULL; 
    bGraphDirected = 0;

}

/* Count adjacent vertices to a vertex.
 * Argument
 *    pVertex   -   Vertex whose adjacent nodes we want to count
 * Returns integer value for count (could be zero)
 */
int countAdjacent(VERTEX_T * pVertex)
{
    int count = 0;
    ADJACENT_T * pAdjacent = pVertex->adjacentHead;
    while (pAdjacent != NULL)
       {
       count += 1;
       pAdjacent = pAdjacent->next;
       }
    return count;
}

/* Set all vertices to the passed color.
 * Argument
 *    A color constant
 */
void colorAll(int color)
{
    VERTEX_T* pVertex = vListHead;
    while (pVertex != NULL)
       {
       pVertex->color = color;
       pVertex = pVertex->next;
       }
}

/* Execute a breadth first search from a vertex,
 * calling the function (*vFunction) on each vertex
 * as we visit it and color it black.
 * Arguments
 *    pVertex    -  starting vertex for traversal
 */
void traverseBreadthFirst(VERTEX_T* pVertex, void (*vFunction)(VERTEX_T*))
{
    VERTEX_T * pCurrent = NULL;
    VERTEX_T * pAdjacent = NULL;    
    queueClear();
    colorAll(WHITE);
    pVertex->color = GRAY;
    enqueue(pVertex);
    while (queueSize() > 0)
       {
       pCurrent = (VERTEX_T*) dequeue();
       if (pCurrent->color != BLACK)
          {
          (*vFunction)(pCurrent);
          pCurrent->color = BLACK;
	  ADJACENT_T* pRef = pCurrent->adjacentHead;
	  while (pRef != NULL)
             {
	     pAdjacent = (VERTEX_T*) pRef->pVertex;
	     if (pAdjacent->color == WHITE)
	         {
		 pAdjacent->color = GRAY;
		 enqueue(pAdjacent);
	         }
	     pRef = pRef->next;
             }
	  }
       } /* end while queue has data */
}


/* Execute a depth first search from a single vertex,
 * calling the function (*vFunction) on the lowest level
 * vertex we visit, and coloring it black.
 * Arguments
 *    pVertex    -  starting vertex for traversal
 */
void traverseDepthFirst(VERTEX_T* pVertex, void (*vFunction)(VERTEX_T*))
{
    VERTEX_T * pAdjacent = NULL;    
    ADJACENT_T* pRef = pVertex->adjacentHead;
    while (pRef != NULL)
       {
       pAdjacent = (VERTEX_T*) pRef->pVertex;
       if (pAdjacent->color == WHITE)
	   {
	   pAdjacent->color = GRAY;
           traverseDepthFirst(pAdjacent,vFunction);
           }
       pRef = pRef->next;  
       } /* end while queue has data */
    /* when we return from the bottom, call the 
     * function and color this node black.
     */
    (*vFunction)(pVertex);
    pVertex->color = BLACK;
}


/* Function to print the information about a vertex
 * Argument  
 *   pVertex   -   vertex we want to print
 */
void printVertexInfo(VERTEX_T* pVertex)
{
    printf("== Vertex key |%s| - data |%s|\n",
	   pVertex->key, (char*) pVertex->data);
}


/********************************/
/** Public functions start here */
/********************************/

/* Initialize or reintialize the graph.
 * Argument 
 *    maxVertices  - how many vertices can this graph
 *                   handle.
 *    bDirected    - If true this is a directed graph.
 *                   Otherwise undirected.
 * Returns 1 unless there is a memory allocation error,
 * in which case it returns zero.
 */
int initGraph(int maxVertices, int bDirected)
{ 
    /* for a linked list graph, we call
     * clearGraph and then initialize bGraphDirected
     */
    clearGraph();
    bMaxVertices = maxVertices; /* 63070501013 - Modified 2022-09-28 - Utilise the use of argument maxVertices */
    bGraphDirected = bDirected; 
    return 1;  /* this implementation of initGraph can never fail */ 
}


/* Add a vertex into the graph.
 * Arguments
 *     key   -   Key value or label for the 
 *               vertex
 *     pData -   Additional information that can
 *               be associated with the vertex.
 * Returns 1 unless there is an error, in which case
 * it returns a 0. An error could mean a memory allocation 
 * error or running out of space, depending on how the 
 * graph is implemented. Returns -1 if the caller tries
 * to add a vertex with a key that matches a vertex
 * already in the graph.
 */
int addVertex(char* key, void* pData)
{
    int bOk = 1;
    VERTEX_T * pPred;
    VERTEX_T * pFound = findVertexByKey(key, &pPred);
    if (pFound != NULL)  /* key is already in the graph */
       {
       bOk = -1;
       }
    else
       {
       VERTEX_T * pNewVtx = (VERTEX_T *) calloc(1,sizeof(VERTEX_T));
       char * pKeyval = strdup(key);
       if ((pNewVtx == NULL) || (pKeyval == NULL))
          {
	  bOk = 0;  /* allocation error */
	  }
       else
          {
	  pNewVtx->key = pKeyval;
          pNewVtx->data = pData;
	  if (vListHead == NULL)  /* first vertex */
	     {
	     vListHead = pNewVtx;
	     }
	  else
	     {
	     vListTail->next = pNewVtx; 
	     }
	  vListTail = pNewVtx;
	  }
       }
    return bOk;
}


/* Remove a vertex from the graph.
 * Arguments
 *     key   -   Key value or label for the 
 *               vertex to remove
 * Returns a pointer to the data stored at that
 * vertex, or NULL if the vertex could not be 
 * found.
 */
void* removeVertex(char* key)
{
   void * pData = NULL; /* data to return */
   VERTEX_T * pPredVtx = NULL;
   VERTEX_T * pRemoveVtx = findVertexByKey(key,&pPredVtx);
   if (pRemoveVtx != NULL)
      {
      /* Get rid of all references to this vertex in ANY EDGE
       */	
      VERTEX_T * pCurrentVtx = vListHead;
      while (pCurrentVtx != NULL)
	{
	if (pCurrentVtx != pRemoveVtx)
	  {
	  /* skip the target vertex */
	  ADJACENT_T* pRef = pCurrentVtx->adjacentHead;
	  ADJACENT_T* pPrevRef = NULL;
	  while (pRef != NULL)
	    {
	    if (pRef->pVertex == pRemoveVtx)  
              /* if this edge involves the one we want to remove*/
	      {
	      if (pPrevRef != NULL)
		{
		pPrevRef->next = pRef->next;
		}
	      else
		{
		pCurrentVtx->adjacentHead = pRef->next;   
		}
	      if (pRef == pCurrentVtx->adjacentTail)
		{
		pCurrentVtx->adjacentTail = NULL;
		}
	      free(pRef);
	      pRef = NULL;
	      break;    /* can only show up once in the adjacency list*/
	      }
	    else
	      {
	      pPrevRef = pRef;
	      pRef = pRef->next;
	      }  
	    }
	  } /* END of code to remove references to vertex in edges */
	pCurrentVtx = pCurrentVtx->next;      
	} 
      freeAdjacencyList(pRemoveVtx);
      if (pPredVtx != NULL)
         {
	 pPredVtx->next = pRemoveVtx->next;
         }
      else /* if there is no predecessor that means this was the head */
         {
         vListHead = pRemoveVtx->next;
         }   
      if (pRemoveVtx == vListTail)
	 vListTail = pPredVtx;
      free(pRemoveVtx->key);
      pData = pRemoveVtx->data;
      free(pRemoveVtx);
      } 
   return pData;
}


/* Add an edge between two vertices
 * Arguments
 *    key1  -  Key for the first vertex in the edge
 *    key2  -  Key for the second vertex
 * Returns 1 if successful, 0 if failed due to
 * memory allocation error, or if either vertex
 * is not found. Returns -1 if an edge already
 * exists in this direction.
 */
int addEdge(char* key1, char* key2)
{
    int bOk = 1;
    VERTEX_T * pDummy = NULL;
    VERTEX_T * pFromVtx = findVertexByKey(key1,&pDummy);
    VERTEX_T * pToVtx = findVertexByKey(key2,&pDummy);
    if ((pFromVtx == NULL) || (pToVtx == NULL))
       {
       bOk = 0;
       }
    else /* CHECK if there is already an edge between these vertices */
       {
       int bEdgeExists = 0;
       ADJACENT_T * pAdjacent = pFromVtx->adjacentHead;
       while ((pAdjacent != NULL) && (!bEdgeExists))
	 {
	 if (pAdjacent->pVertex == pToVtx)
          {
	  bEdgeExists = 1;  /* the 'To' vertex is already in the 'From' vertex's adjacency list */ 
          }
	 else
	   {
	   pAdjacent = pAdjacent->next;
	   }
	 } 
       if (bEdgeExists)
	 bOk = -1;	   
       }
    if (bOk == 1) /* no errors so far */
       {
       ADJACENT_T * pNewRef = (ADJACENT_T*) calloc(1,sizeof(ADJACENT_T));
       if (pNewRef == NULL)
          {
	  bOk = 0;
          }
       else
          {
	  pNewRef->pVertex = pToVtx;
	  if (pFromVtx->adjacentTail != NULL)
              {
	      pFromVtx->adjacentTail->next = pNewRef;
	      }
          else
	      {
	      pFromVtx->adjacentHead = pNewRef;
	      }
	  pFromVtx->adjacentTail = pNewRef;
          } 
       } 
    /* If undirected, add an edge in the other direction */
    if ((bOk) && (!bGraphDirected))
       {
       ADJACENT_T * pNewRef2 = (ADJACENT_T*) calloc(1,sizeof(ADJACENT_T));
       if (pNewRef2 == NULL)
          {
	  bOk = 0;
          }
       else
          {
	  pNewRef2->pVertex = pFromVtx;
	  if (pToVtx->adjacentTail != NULL)
              {
	      pToVtx->adjacentTail->next = pNewRef2;
	      }
          else
	      {
	      pToVtx->adjacentHead = pNewRef2;
	      }
	  pToVtx->adjacentTail = pNewRef2;
          } 
       } 
    return bOk;
}


/* Remove an edge between two vertices
 * Arguments
 *    key1  -  Key for the first vertex in the edge
 *    key2  -  Key for the second vertex
 * Returns 1 if successful, 0 if failed 
 * because either vertex is not found or there
 * is no edge between these items.
 */
int removeEdge(char* key1, char* key2)
{
   int bOk = 1;
   VERTEX_T * pDummy = NULL;
   VERTEX_T * pFromVtx = findVertexByKey(key1,&pDummy);
   VERTEX_T * pToVtx = findVertexByKey(key2,&pDummy);
   if ((pFromVtx == NULL) || (pToVtx == NULL))
     {
     bOk = 0;
     }
   else 
     {
     int bEdgeExists = 0;
     ADJACENT_T * pAdjacent = pFromVtx->adjacentHead;
     while ((pAdjacent != NULL) && (!bEdgeExists))
       {
       if (pAdjacent->pVertex == pToVtx)
	 {
	 bEdgeExists = 1;  /* the 'To' vertex is connected to the 'From' vertex */ 
	 }
       else
	 {
	   pAdjacent = pAdjacent->next;
	 }
       } 
     if (!bEdgeExists) /* No edge to remove */
	 bOk = -1;	   
     }
   if (bOk == 1)
     {
     ADJACENT_T* pRef = pFromVtx->adjacentHead;
     ADJACENT_T* pPrevRef = NULL;
     while (pRef != NULL)
       {  
       if (pRef->pVertex == pToVtx)  /* if this edge involves the target*/
	 {
	 if (pPrevRef != NULL)
	   {
	   pPrevRef->next = pRef->next;
	   }
	 else
	   {
	   pFromVtx->adjacentHead = pRef->next;   
	   }
	 if (pRef == pFromVtx->adjacentTail)
	   {
	   pFromVtx->adjacentTail = NULL;
	   }
	 free(pRef);
	 break;    /* can only show up once in the adjacency list*/
	 }
       else
	 {
	 pPrevRef = pRef;
	 pRef = pRef->next;
	 }  
       }
       /* If undirected, remove edge in the other direction */
       if ((bOk) && (!bGraphDirected))
         {
         ADJACENT_T* pRef2 = pToVtx->adjacentHead;
	 ADJACENT_T* pPrevRef2 = NULL;
	 while (pRef2 != NULL)
	   {   
	   if (pRef2->pVertex == pFromVtx)  
	     {
	     if (pPrevRef2 != NULL)
	       {
	       pPrevRef2->next = pRef2->next;
	       }
	     else
	       {
	       pToVtx->adjacentHead = pRef2->next;   
	       }
	     if (pRef2 == pToVtx->adjacentTail)
	       {
	       pToVtx->adjacentTail = NULL;
	       }
	     free(pRef2);
	     break;    /* can only show up once in the adjacency list*/
	     }
	   else
	     {
	     pPrevRef2 = pRef2;
	     pRef2 = pRef2->next;
	     }  
	   }
	 }
     }
   return bOk;
}

/* Find a vertex and return its data
 * Arguments
 *    key  -  Key for the vertex to find
 * Returns the data for the vertex or NULL
 * if not found.
 */
void* findVertex(char* key)
{
    void* pData = NULL;
    VERTEX_T * pDummy = NULL;
    VERTEX_T * pFoundVtx = findVertexByKey(key,&pDummy);
    if (pFoundVtx != NULL)
       {
       pData = pFoundVtx->data;
       }
    return pData; 
}


/* Return an array of copies of the keys for all nodes
 * adjacent to a node. The array and its
 * contents should be freed by the caller when it 
 * is no longer needed.
 * Arguments
 *    key   -  Key for the node whose adjacents we want
 *    pCount - Return number of elements in the array
 * Returns array of char* which are the keys of adjacent
 * nodes. Returns number of adjacent vertices in pCount.
 * If pCount holds -1, the vertex does not exist.
 */
char** getAdjacentVertices(char* key, int* pCount)
{
    char** keyArray = NULL;
    VERTEX_T * pDummy = NULL;
    VERTEX_T * pFoundVtx = findVertexByKey(key,&pDummy);
    if (pFoundVtx != NULL)
       {
       *pCount = countAdjacent(pFoundVtx);
       if (*pCount > 0)
          {
	  int i = 0;
	  keyArray = (char**) calloc(*pCount, sizeof(char*));
          if (keyArray != NULL)
	     {
	     ADJACENT_T * pAdjacent = pFoundVtx->adjacentHead;
	     while (pAdjacent != NULL)
	        {
		VERTEX_T* pVertex = (VERTEX_T*) pAdjacent->pVertex;
		keyArray[i] = strdup(pVertex->key);
		pAdjacent = pAdjacent->next;
		i += 1;
	        }
	     }
          } 
       } 
    else
       {
       *pCount = -1;
       }
    return keyArray;
}


/* Print out all the nodes reachable from a node by a 
 * breadth-first search.
 * Arguments
 *   startKey   -  Key for start vertex
 * Returns 1 if successful, -1 if the vertex does not exist.
 */
int printBreadthFirst(char* startKey)
{
   int retval = 1;
   VERTEX_T * pDummy = NULL;
   VERTEX_T * pVertex = findVertexByKey(startKey,&pDummy);
   if (pVertex == NULL)
      {
      retval = -1;
      }
   else
      {
      traverseBreadthFirst(pVertex,&printVertexInfo);
      }
   return retval;
}

/* Print out all the nodes by a depth-first search.
 */
void printDepthFirst()
{
   VERTEX_T* pVertex = vListHead;
   if (pVertex == NULL)
      {
      printf("The graph is empty\n");
      }
   else
      {
      colorAll(WHITE);
      while (pVertex != NULL)
         {
	 if (pVertex->color == WHITE)
	    {
	    printf("\nStarting new traversal from |%s|\n",
                   pVertex->key);
	    pVertex->color = GRAY;
            traverseDepthFirst(pVertex,&printVertexInfo);
	    }
         pVertex = pVertex->next;
	 }
      }
}

 

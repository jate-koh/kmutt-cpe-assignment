/* 63070501013 - Modified 2022-09-29 
- Create a new header file, so that all type def struct can
be easily managed here */
#ifndef GRAPHSTRUCT_H
#define GRAPHSTRUCT_H

/* Structure type for user in the social network
 * This will be the data stored at a vertex of the network.
 */
typedef struct
{
  char username[32];
  char firstname[32];
  char lastname[32];
  char birthday[16];
} USER_T;

/* List items for the adjacency list.
 * Each one represents an edge leading to an existing vertex
 */
typedef struct _adjVertex
{
  void *pVertex;           /* pointer to the VERTEX_T this
                            * item refers to.
                            */
  struct _adjVertex *next; /* next item in the ajacency list */
} ADJACENT_T;

/* Vertex structure - repeated from linkedListGraph */
typedef struct _vertex
{
  char *key;                /* key for this vertex */
  void *data;               /* additional data for this vertex */
  int color;                /* used to mark nodes as visited */
  struct _vertex *next;     /* next vertex in the list */
  ADJACENT_T *adjacentHead; /* pointer to the head of the
                             * adjacent vertices list
                             */
  ADJACENT_T *adjacentTail; /* pointer to the tail of the
                             * adjacent vertices list
                             */
} VERTEX_T;

/* Structure that represents one item of the list */
typedef struct _listitem
{
  void *data;             /* Pointer to the data for this node */
  struct _listitem *next; /* Link to the next item in the list */
} LISTITEM_T;

#endif

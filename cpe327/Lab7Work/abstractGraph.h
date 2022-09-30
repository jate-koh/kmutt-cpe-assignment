/*  
 *  abstractGraph.h
 *
 *  Defines the necessary functions for a graph
 *
 *  Created by Sally Goldin on 18 Jan 2012 for CPE 113
 *
 */
#ifndef ABSTRACTGRAPH_H
#define ABSTRACTGRAPH_H
#include "graphStruct.h"
/* 63070501013 - Modified 2022-09-29
- Add defined graphStruct.h */

/* Initialize the graph.
 * Argument 
 *    maxVertices  - how many vertices can this graph
 *                   handle.
 *    bDirected    - If true this is a directed graph.
 *                   Otherwise undirected.
 * Returns 1 unless there is a memory allocation error,
 * in which case it returns zero.
 */
int initGraph(int maxVertices, int bDirected);


/* Free all memory associated with the graph and
 * reset all parameters.
 */
void clearGraph();


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
int addVertex(char* key, void* pData);


/* Remove a vertex from the graph.
 * Arguments
 *     key   -   Key value or label for the 
 *               vertex to remove
 * Returns a pointer to the data stored at that
 * vertex, or NULL if the vertex could not be 
 * found.
 */
void* removeVertex(char* key);


/* Add an edge between two vertices
 * Arguments
 *    key1  -  Key for the first vertex in the edge
 *    key2  -  Key for the second vertex
 * Returns 1 if successful, 0 if failed due to
 * memory allocation error, or if either vertex
 * is not found. Returns -1 if an edge already
 * exists in this direction.
 */
int addEdge(char* key1, char* key2);


/* Remove an edge between two vertices
 * Arguments
 *    key1  -  Key for the first vertex in the edge
 *    key2  -  Key for the second vertex
 * Returns 1 if successful, 0 if failed 
 * because either vertex is not found or there
 * is no edge between these items.
 */
int removeEdge(char* key1, char* key2);


/* Find a vertex and return its data
 * Arguments
 *    key  -  Key for the vertex to find
 * Returns the data for the vertex or NULL
 * if not found.
 */
void* findVertex(char* key);

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
char** getAdjacentVertices(char* key, int* pCount);


/* Print out all the nodes reachable from a node by a 
 * breadth-first search.
 * Arguments
 *   startKey   -  Key for start vertex
 * Returns 1 if successful, -1 if the vertex does not exist.
 */
int printBreadthFirst(char* startKey);


/* Print out all the nodes by a depth-first search.
 */
void printDepthFirst();


/* Return information as to whether two vertices are
 * connected by a path.
 * Arguments
 *    key1 -  Key for the start vertex 
 *    key2 -  Key for the second vertex to check 
 * Returns 1 if the two vertices are connected, 0 if they
 * are not. Returns -1 if either vertex does not exist.
 */
int isReachable(char* key1, char* key2);

VERTEX_T *findVertexByKey(char *key, VERTEX_T **pPred);
/*  63070501013 - Modified 2022-09-29 
- Moved find vertex into abstract, so it can be initialized immediately */

#endif

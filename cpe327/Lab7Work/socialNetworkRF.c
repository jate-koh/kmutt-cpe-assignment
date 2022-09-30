/**
 * Simple social network
 * This code reads a file that defines vertices and edges
 * and builds a graph using the graph ADT defined in abstractGraph.h.
 * It then allows the user to search for users, display their friends,
 * suggest new friends, and add new friends.
 * Version created 16 September 2021 with deliberate Code Smells,
 * for CPE327 Refactoring Exercise
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "graphStruct.h" 
/* 63070501013 - Modified 2022-09-29 - Include defined social
 network graph structure from header file, so this file is less packed */
#include "abstractGraph.h"
#include "abstractMenu.h"
/*63070501013 - Modified 2022-09-29 - Include abstarctMenu header,
so that socialNetworkRF.c can use function from menu.c */

/* 63070501013 - Modified 2022-09-29 - All struct here are move
to graphStruct header, so this file is less packed */

/* 63070501013 - Modified 2022-08-28 - Seperates open files into new function 
so that buildNetwork will not be too 'Godly' */
/* Open files by passing filename into arguement 
 * Return NULL if file cannot be read 
 * Return file pointer if file is successfully read */
FILE* openFiles(char* filename) {
  FILE* pIn = NULL;
  pIn = fopen(filename,"r");
  if (pIn == NULL) {
    printf("Error - cannot open file '%s'\n", filename);
    return NULL;  
  }
  return pIn;
}

/* Initializes and builds the network based on
 * the information in the file.
 * Returns 1 if successful, 0 if any error occurred
 */
int buildNetwork(char * filename)
{
  FILE* pIn = NULL;
  char inputline[128];
  char username[32];
  char username2[32];  /* second user in a 'FRIEND' line */
  char firstname[32];
  char lastname[32];
  char birthday[16];
  USER_T * pUser = NULL;   /* used for dynamically allocating users */
  int status = 1; /* used to test for errors */
  
  /* 63070501013 - Modified 2022-08-28 - Seperates open files into new function */
  pIn = openFiles(filename);
  if(pIn == NULL) return 0; /* Failed to build network if file can't be read */

  status = initGraph(1000,0); /* this should be an UNDIRECTED graph */
  if (!status)
    {
    printf("Error initializing the graph\n");
    return status;  
    }
  /* Let's read the file and build the network */
  while (fgets(inputline,sizeof(inputline),pIn) != NULL)
    {
      printf("read %s", inputline);
    /* if this is a FRIEND specification, create an edge */  
    if (strncmp(inputline,"FRIEND",strlen("FRIEND")) == 0)
      {
      if (sscanf(inputline,"FRIEND %s %s",username,username2) != 2)
	{
	printf("Invalid format for FRIEND line: '%s'",inputline);  
	}
      else
	{
	status = addEdge(username,username2);
	if (status == 0)
	  printf("At least one user ('%s' and '%s') does not exist\n",
		 username,username2);
	else if (status < 0)
	  printf("'%s' and '%s' are already friends\n",
		 username,username2);
	else
	  printf("'%s' and '%s' are now friends\n", username,username2);
	}
      }
    else /* this is a user (VERTEX) */
      {
      if (sscanf(inputline,"%s %s %s %s",username,firstname,lastname,birthday) != 4)
          printf("Invalid input line, skipping: '%s'",inputline);
      else
          {
	  VERTEX_T * dummy;  
	  VERTEX_T* v = findVertexByKey(username,&dummy);  
	  if (v != NULL)
	    {
	    printf(">> ERROR in data file! User '%s' already exists\n",username);
	    }
	  else
	    {
	    pUser = calloc(1,sizeof(USER_T));
	    if (pUser != NULL)
	      {
	      strcpy(pUser->username,username);  
	      strcpy(pUser->firstname,firstname);  
	      strcpy(pUser->lastname,lastname);  
	      strcpy(pUser->birthday,birthday);
	      status = addVertex(username,pUser);
	      if (status == 0)
		printf(">> Memory allocation error adding user '%s'\n",username);
	      else if (status < 0)
		printf(">> ERROR in data file! User '%s' already exists\n",username);
	      else   
		printf("Successfully added user '%s' to the social network\n",username);
	      }
	    else
	      {
	      printf("Memory allocation error creating a user\n");
	      return 0;
	      }
	    }
	  }	
      }
    }
  fclose(pIn);
  return 1;
}

/* 63070501013 - Modified 2022-09-29 
- Functions that used to be here have been move to
other modules in order to make this file less packed */

/* main function builds the graph, then enters a loop 
 * doing different operations 
 */
int main(int argc, char* argv[])
{
  int status = 1;
  int friendcount = 0;
  char inputline[32];
  char username[32] ="";
  int option = 0;
  USER_T * myProfile = NULL;
  if (argc < 2)
    {
    printf("Please provide an input file name: ./socialNetwork [file]\n");
    exit(0);
    }
  status = buildNetwork(argv[1]);
  if (!status)
    {
    printf("Fatal error building the social network\n");
    exit(1);
    }
  while (option != 5)
    {
    option = getMenuChoice();
    switch(option)
      {
      case 1:
        createNewUser();
        /* 63070501013 - Modified 2022-09-29 
        - transform case 1 statement into function in menu.c 
        so that it can be easily manage and make this file less packed */
	    break;
      case 2:
        showUserFriend();
	    break;
      case 3:
        suggestUserFriend();
	    break;
      case 4:
        addUserFriend();
	    break;
      case 5:
	    break;
      }
    }
  printf("\nGoodbye!\n");
}

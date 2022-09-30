/* Module to control menu for socialNetwork program
 *   Created by Sally Goldin 16 September 2021
 */
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
/* 63070501013 - Modified 2022-09-29 
- include stdlib.h because it is needed */
#include "abstractGraph.h"
/* 63070501013 - Modified 2022-09-29
- include abstractGraph haeder too, because some 
function need its definition */
#include "graphStruct.h"
/* 63070501013 - Modified 2022-09-29
- include graphStruct.h in menu.c too, because some 
function need its definition */
#include "abstractMenu.h"
/* 63070501013 - Modified 2022-09-29 
- include abstract header of menu, so that it will be
like a chain of implementation of menu functions */

/* Present a menu of actions and ask the user which one they
 * want. Keep asking until a valid option is provided.
 * Return the selected option.
 */
int getMenuChoice()
{
  char input[32];
  int choice = 0;
  printf("\nAvailable actions:\n");
  printf("\t1 - Create a new user\n");
  printf("\t2 - Show a user's friends\n");
  printf("\t3 - Suggest new friends\n");
  printf("\t4 - Add new friends\n");
  printf("\t5 - Exit the program\n");
  while ((choice < 1) || (choice > 5))
    {
    printf("What action? ");  
    fgets(input,sizeof(input),stdin);
    sscanf(input,"%d",&choice);
    }
  return choice;
}
  /* Create a new user and add to the social network.
 *   username     Username for the new user 
 * Returns pointer to the USER_T constructed by this function
 * or NULL if an error.
 */

/* 63070501013 - Modified 2022-09-29
- put addFriends into menu.c because other function
need it */
/* Create friend relationships with the focus user
 *  focusUser      User for whom we are creating friends
 *                 Assumes this user exists
 */
void addFriends(USER_T * focusUser)
{
  char input[32];
  char friendname[32];
  int status = 1;
  int done = 0;
  printf("\nAdding friends for user '%s %s (%s)'\n",focusUser->firstname,
	 focusUser->lastname,focusUser->username);  
  while (!done) /* 63070501013 - Modified 2022-09-28 - while(1) can get out of 
  hand, it could use better conditional statement */
    {
    printf("\tWho do you want to add (username)? (Type 'DONE' to stop) ");
    fgets(input,sizeof(input),stdin);
    sscanf(input,"%s",friendname);
    /* 63070501013 - Modified 2022-09-28 - breaking loop isn't necessary if while have new condition */
    if (strcasecmp(friendname,"DONE") != 0) { 
      if (strcmp(friendname,focusUser->username) == 0) printf("\t>>You can't add yourself as a friend!\n");
      else {
       status = addEdge(focusUser->username,friendname);
       if (status == 0) {
        printf("\t>> User '%s' does not exist\n",friendname);
       }
       else if (status < 0) {
        printf("\t>> User '%s' is already your friend\n",friendname);
       }
       else {
        printf("\t>> User '%s' is now your friend\n",friendname);
       } /* 63070501013 - Modified 2022-08-29 - Add brackets to all if-else statement, so it fit my format */
      }
    }
    else done = 1; //If user is done, its value is change here
    }
}


/* 63070501013 - Modified 2022-09-29
- put searchExisting into menu.c because other function
need it */
/* Look through a list of existing friend usernames
 * to make sure the target username does not appear.
 *    targetUsername           Name we're looking for
 *    friendnames              Array of keys
 *    count                    Size of friendnames
 * Returns 1 if found, 0 if not found
 */
int searchExisting(char* targetUsername, char** friendnames, int count)
{
  int i = 0;
  int found = 0;
  for (i = 0; (i < count) && (!found); i++)
    if (strcmp(targetUsername,friendnames[i]) == 0)
      found = 1;
  return found;
}

/* 63070501013 - Modified 2022-09-29
- put printFriends into menu.c because other function
need it */
/* This function will print the names of all the friends
 * of the passed user. It will return the number of friends
 * found (which could be 0).
 * The function is used both for simply printing friends of a user (Case1)
 * and for suggesting new friends (by printing the friends of friends) (Case 2)
 *   pUser                  User whose friends we are printing
 *   pExcludeUser           If non-NULL, we are printing suggestions (Case 2)
 *                          So we will not print the header, and we
 *                          will exclude this username if he or she
 *                          appears in the suggestion list
 *   userFriends            Only specified if pExcludeUser is non-null (Case 2)
 *                          holds the keys for all of pExcludeUser's friends
 *                          so we don't suggest someone with whom the
 *                          user is already connected.
 *   count                  0 if userFriends is NULL, else size of userFriends
 */
int printFriends(USER_T * pUser,  USER_T * pExcludeUser,
		 char** userFriends, int count)
{
  int i = 0;
  int friendCount = 0;
  int adjustedFriendCount = 0;  /* number of friends who were not excluded */
  char** friendKeys = getAdjacentVertices(pUser->username,&friendCount);
  if (friendCount > 0)
    {
    adjustedFriendCount = friendCount;  
    if (pExcludeUser == NULL)  
       printf("Here is a list of your current friends:\n");
    for (i = 0; i < friendCount; i++)
      {
      USER_T * friend = findVertex(friendKeys[i]);
      if (friend == NULL)
	printf("Something is wrong - can't find USER_T for key '%s'\n",
	       friendKeys[i]);
      else if (friend == pExcludeUser) /* exclude me! */
	adjustedFriendCount--;
      else 
	{
	/* exclude people who are already my friends */  
	if (!searchExisting(friendKeys[i],userFriends,count))
	    {
	    printf("\t%s %s (%s) - Birthday %s\n",
	       friend->firstname,friend->lastname,friend->username,
	       friend->birthday);
	    }
	else
	   adjustedFriendCount--;
	}
      }
    }
  else
    {
    if (pExcludeUser == NULL) /* Case 1, when there are 0 adjacents */
	printf("You do not have any friends yet\n");
    }
  if (friendKeys != NULL)
    free(friendKeys);
  return adjustedFriendCount;
}

/* 63070501013 - Modified 2022-09-29
- put suggestFriends into menu.c because other function
need it */
/* Suggest as new friends people who are friends of your friends.
 *   pUser -- focus user for whom we are suggesting friends.
 */
void suggestFriends(USER_T * pUser)
{
 int i = 0;
 int dummyCount = 0;
 int friendCount = 0;
 char** friendKeys = getAdjacentVertices(pUser->username,&friendCount);
 if (friendCount > 0)
    {
    printf("\n-- Here are some people you might like to know --\n");  
    for (i = 0; i < friendCount; i++)
      {
      USER_T * friend = findVertex(friendKeys[i]);
      if (friend != NULL)
	  {
	  printf("  Friends of %s whom you might like: \n",friend->username);  
	  dummyCount = printFriends(friend,pUser,friendKeys,friendCount);
	  if (dummyCount == 0)
   	     printf("\t>> %s has no friends who aren't already your friends\n",friend->username);
	  }
      else
	  printf("\t\tSomething is very wrong - no user associated with username '%s'?\n", friendKeys[i]);
      }
    }
}

/* 63070501013 - Modified 2022-09-29
- put addUser into menu.c because other function
need it */
USER_T * addUser(char* username)
{
  USER_T * newUser = NULL;
  char input[32];
  newUser = calloc(1,sizeof(USER_T));
  if (newUser != NULL)
    {
    printf("\nCreating a new user profile for '%s'\n",username);  
    strcpy(newUser->username,username);   
    printf("\tWhat is your first name? ");
    fgets(input,sizeof(input),stdin);
    sscanf(input,"%s",newUser->firstname);
    printf("\tWhat is your last name? ");
    fgets(input,sizeof(input),stdin);
    sscanf(input,"%s",newUser->lastname);
    printf("\tWhat is your birthday (dd-mm-yyyy)? ");
    fgets(input,sizeof(input),stdin);
    sscanf(input,"%s",newUser->birthday);
    if (addVertex(username,newUser) != 1)
      {
      printf("Error adding new user\n");
      free(newUser);
      newUser = NULL;
      }
    }
  return newUser;
}

/* 63070501013 - Modified 2022-09-29 - 
transform case 1 statement into function, to make it
look simpler and easy to manage */
USER_T *createNewUser() {
  char inputline[32];
  char username[32] = "";
  USER_T *myProfile = NULL;
  printf("\nEnter new username: ");
	fgets(inputline,sizeof(inputline),stdin);
	sscanf(inputline,"%s",username);
	myProfile = findVertex(username);
	if (myProfile == NULL)
	  {
	    myProfile = addUser(username);
	  }
	else
	  {
	    printf("\tSorry, that username is already in use\n");
	  }
}

/* 63070501013 - Modified 2022-09-29 - 
transform case 2 statement into function, to make it
look simpler and easy to manage */
void showUserFriend() {
  char inputline[32];
  char username[32] = "";
  USER_T *myProfile = NULL;
  printf("\nPrint friends for what user? ");
	fgets(inputline,sizeof(inputline),stdin);
	sscanf(inputline,"%s",username);
	myProfile = findVertex(username);
	if (myProfile != NULL)
	  {
	    printFriends(myProfile,NULL,NULL,0);
      /* 63070501013 - Modified 2022-09-30
      - remove friendCount, it has no use here */
	  }
	else
	  {
	    printf("\tNo user exists with that username\n");
	  }
}

/* 63070501013 - Modified 2022-09-29 - 
transform case 3 statement into function, to make it
look simpler and easy to manage */
void suggestUserFriend() {
  char inputline[32];
  char username[32] = "";
  USER_T *myProfile = NULL;
  int friendcount = 0;
  printf("\nSuggest friends for what user? ");
	fgets(inputline,sizeof(inputline),stdin);
	sscanf(inputline,"%s",username);
	myProfile = findVertex(username);
	if (myProfile != NULL)
	  {  
	    friendcount = printFriends(myProfile,NULL,NULL,0);
	    if (friendcount > 0)
	      suggestFriends(myProfile);
	  }
	else
	  {
	    printf("\tNo user exists with that username\n");
	  }
}

/* 63070501013 - Modified 2022-09-29 - 
transform case 4 statement into function, to make it
look simpler and easy to manage */
void addUserFriend() {
  char inputline[32];
  char username[32] = "";
  USER_T *myProfile = NULL;
  printf("\nAdd friends for what user? ");
	fgets(inputline,sizeof(inputline),stdin);
	sscanf(inputline,"%s",username);
	myProfile = findVertex(username);
	if (myProfile != NULL)
	  {
	    addFriends(myProfile);
	  }
	else
	  {
	    printf("\tNo user exists with that username\n");
	  }
}



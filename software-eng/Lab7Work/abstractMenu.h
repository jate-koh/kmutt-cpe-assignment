/* 63070501013 - Modified 2022-09-29 
- create abstract header for menu, so that menu.c can be reference
and can be include as header in socialNetworkRF.c */
#ifndef ABSTRACTMENU_H
#define ABSTRACTMENU_H
#include "graphStruct.h"

int getMenuChoice();

void addFriends(USER_T *focusUser);

int searchExisting(char* targetUsername, char** friendnames, int count);

int printFriends(USER_T * pUser,  USER_T * pExcludeUser, char** userFriends, int count);

void suggestFriends(USER_T * pUser);

USER_T *addUser(char *username);

void showUserFriend();

void suggestUserFriend();

void addUserFriend();

USER_T *createNewUser();


/*  63070501013 – Modified 2022-09-29
- function is moved from socialNetworkRF to menu.c and 
abstractMenu.h, because it will make the file less packed */
#endif

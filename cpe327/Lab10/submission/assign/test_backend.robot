*** Settings ***
Library           RequestsLibrary

*** Variables ***
${url}    https://petstore.swagger.io
${path}    /#/pet/findByStatus

*** Test Cases ***
Request using GET to find pet: 63070501013 Jatetanan
    Create Session    post_find_pet   ${url}    disable_warnings=1
    &{category_data}=     Create Dictionary       id=0                      name=string        
    &{json_data}=         Create Dictionary       id=9222968140497181000    category= &{category_data}
    ${response}=          GET                     ${url}                    available
    Should Be Equal As Strings   ${response.status_code}    200
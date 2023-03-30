*** Settings ***
Library           RequestsLibrary

*** Variables ***
${url}                      https://reqres.in
${post_create_user_api}     /api/users

*** Test Cases ***
TC_API_00001 Request to POST a creat users API
    Create Session    post_create_user   ${url}    disable_warnings=1
    &{json_data}=    Create Dictionary    name=kmutt_user    job=student
    ${response}=    POST On Session    post_create_user    ${post_create_user_api}    json=${json_data}
    Should Be Equal As Strings   ${response.status_code}    201
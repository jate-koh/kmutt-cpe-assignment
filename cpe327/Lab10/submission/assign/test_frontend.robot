*** Settings ***
Library        SeleniumLibrary

*** Variables ***
${browser}        chrome
${url}            https://www.saucedemo.com
${username}       standard_user
${password}       secret_sauce

*** Test Cases ***
Test UI of SauceDemo.com : 63070501013 Jatetanan
    Open Browser     ${url}    ${browser}
    Input Text       name=user-name    ${username}
    Input Text       name=password     ${password}
    Wait Until Element Is Visible    name=login-button
    Click Element    name=login-button
    Element Text Should Be    //*[@class="title"]    PRODUCTS
    Close Browser
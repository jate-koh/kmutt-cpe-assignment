*** Settings ***
Library             SeleniumLibrary

*** Variables ***
${browser}          chrome
${google_url}       http://www.google.com
${search_text}      truemoney

*** Test Cases ***
TC_UI_00001 Search Google
    Open Browser  ${google_url}  ${browser}
    Input text  name=q    ${search_text}
    Wait Until Element Is Visible    name=btnK
    Click Element    name=btnK
    Page Should Contain Element    //*[@id="rso"]
    Close Browser
function cookie() {
    const name = document.querySelector("#userEmail")
    const pass = document.querySelector("#userPass")
    const submitbtn = document.querySelector("#submit_button")

    submitbtn.addEventListener("click",()=>{
        setCookie("name",name.value,365)
        setCookie("pass",pass.value,365)

        console.log(document.cookie)
        console.log(getCookie(name))
        console.log(getCookie(pass))
    })

}

function getCookie(key){
    const cDecoded = decodeURIComponent(document.cookie);
    const cArray = cDecoded.split(";");

    let result = null

    cArray.forEach(element =>{
        if(element.indexOf(key) == 0){
            result = element.substring(key.length+1)
        }
    })
    return result
}

function deleteCookie(key){
    setCookie(key, null, null);
}

function setCookie(key,value,day){
    const date = new Date();
    date.setTime(date.getTime() +  (day * 24 * 60 * 60 * 1000));
    let expires = "expires=" + date.toUTCString();
    document.cookie = `${key}=${value}; ${expires}; path=/login`
}
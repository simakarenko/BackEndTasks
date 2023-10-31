const userName = document.querySelector("input[type='text']");

userName.addEventListener("input", ()=>{
    const patternName = /^[A-z0-9_]{3,20}$/i;

    if(patternName.test(userName.value)){
        userName.classList.add("success");
        userName.classList.remove("error");
    }
    else{
        userName.classList.remove("success");
        userName.classList.add("error");
    }
})

const userPassword = document.querySelector("input[type='password']");

userPassword.addEventListener("input",()=>{
    const patternPassword = /^[A-z0-9_]{8,20}$/i;

    if(patternPassword.test(userPassword.value)){
        userPassword.classList.add("success");
        userPassword.classList.remove("error");
    }
    else{
        userPassword.classList.remove("success");
        userPassword.classList.add("error");
    }
})

// const closeButton = document.querySelector(".button-close");

// closeButton.addEventListener("click", ()=>{
//     const loginBlock = document.querySelector(".login");
//     loginBlock.classList.add("display-hide")
// })

loginUser = () =>{
    let userName = document.querySelector("#username").value;
    let userPassword = document.querySelector("#password").value;

    let data = {
        username: userName,
        password: userPassword
    }

    let xhr = new XMLHttpRequest ();
    xhr.open("POST", "backend_login", true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.addEventListener("readystatechange", ()=>{
        if(xhr.readyState === 4 && xhr.status === 200){
            let response = JSON.parse(xhr.response);
            if(response.success){
                console.log("Аутентифікація успішна!");
            }else{
                console.error("Помилка аутентифікації!");
            }
        }
        else{
            console.error("Помилка сервера: " + xhr.status);
        }
    })

    xhr.send(JSON.stringify(data));
}

document.querySelector(".login-button").addEventListener("click", loginUser);
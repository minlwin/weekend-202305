function showLength<T, K extends keyof T>(data:T, key:K) {
    console.log(data[key])
} 

let object = {
    name: "Thidar",
    age: 20,
    job: "Student"
}

showLength(object, "name")
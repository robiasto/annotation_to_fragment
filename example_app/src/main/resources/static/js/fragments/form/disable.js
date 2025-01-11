document.addEventListener("alpine:init", () => {
    document.querySelectorAll("[disable-fields=true]").forEach((el) => {
        el.querySelectorAll("[form-field]").forEach((field) => {
            field.disabled = true;
        });

        el.querySelectorAll(".on-disable-hide").forEach((element) => {
            console.log(element);
            element.style.display = "none";
        });
    });
});

document.addEventListener("alpine:init", () => {
    Alpine.data("imageButton", () => ({
        addImage(inputId) {
            this.$el.blur();
            console.log(this.$el.parentNode);
            document.getElementById(inputId).click();
        },
        updateDsipalyedImg(imageId) {
            const imgTagId = this.$el.parentNode.querySelector("img");
            imgTagId.src = window.URL.createObjectURL(this.$el.files[0]);
            imgTagId.classList.remove("p-6");
        },
    }));
});

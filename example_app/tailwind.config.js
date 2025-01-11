const defaultTheme = require("tailwindcss/defaultTheme");

module.exports = {
    content: ["./src/main/resources/**/*.html", "./src/main/java/**/*.html"],
    theme: { extend: {} },
    plugins: [require("@tailwindcss/typography"), require("@tailwindcss/aspect-ratio"), require("@tailwindcss/forms")],
};

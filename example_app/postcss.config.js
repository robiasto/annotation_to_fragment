const postcssConfig = {
    plugins: {
        "postcss-import": {},
        "tailwindcss/nesting": "postcss-nesting",
        tailwindcss: {},
        autoprefixer: {},
    },
};

if (process.env.NODE_ENV === "production") {
    postcssConfig.plugins = {
        "postcss-import": {},
        "tailwindcss/nesting": "postcss-nesting",
        tailwindcss: {},
        autoprefixer: {},
        cssnano: {
            preset: "default",
        },
    };
}

module.exports = postcssConfig;

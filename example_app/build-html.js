const fs = require("fs");
const path = require("path");

function copyRecursiveSync(src, dest) {
    if (fs.statSync(src).isDirectory()) {
        fs.readdirSync(src).forEach((childItemName) => {
            copyRecursiveSync(path.join(src, childItemName), path.join(dest, childItemName));
        });
    }

    if (path.extname(src) === ".html" || path.extname(src) === ".svg") {
        fs.mkdirSync(path.dirname(dest), { recursive: true });
        fs.copyFileSync(src, dest);
    }
}

function buildTarget(targetDir) {
    copyRecursiveSync(path.join(__dirname, "./src/main/resources/templates"), targetDir);
    copyRecursiveSync(path.join(__dirname, "./src/main/java"), path.join(targetDir, "fragments"));
}

//buildTarget(path.join(__dirname, "./out/production/resources/templates"));
buildTarget(path.join(__dirname, "./build/resources/main/templates"));

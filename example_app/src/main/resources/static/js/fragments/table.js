document.addEventListener("alpine:init", () => {
    document.querySelectorAll("table").forEach((table) => {
        table.querySelectorAll("th").forEach((tableHeadCol) => {
            if (tableHeadCol.classList.contains("max-xl:hidden")) {
                hideTableCol(table, tableHeadCol.dataset.colName);
            }
        });
    });

    function hideTableCol(table, colName) {
        table.querySelectorAll("[data-col-name=" + colName + "]").forEach((col) => {
            col.classList.add("max-xl:hidden");
        });
    }
});

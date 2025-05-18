/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/main/webapp/**/*.xhtml", // <- add this for JSF/XHTML
    "./src/main/webapp/**/*.html",  // if you use any HTML too
    "./src/main/java/**/*.java", // <- add this for Java classes
    "./src/main/resources/**/*.xml", // <- add this for XML files
  ],
  theme: {
    extend: {
      colors: {
        
      }
    },
  },
  plugins: [],
}

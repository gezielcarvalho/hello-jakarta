<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Simple Form</title>
    <script>
      function validateForm() {
        const textField = document.forms["demoForm"]["name"].value.trim();
        const checkbox = document.forms["demoForm"]["agree"].checked;

        if (textField === "" || !checkbox) {
          alert("Please enter a name and agree to continue.");
          return false;
        }

        return true;
      }
    </script>
  </head>
  <body>
    <h2>Simple Form</h2>
    <form name="demoForm" method="post" action="submit" onsubmit="return validateForm();">
      <label for="name">Your Name:</label>
      <input type="text" id="name" name="name" /><br/><br/>

      <label>
        <input type="checkbox" name="agree" /> I agree to the terms
      </label><br/><br/>

      <input type="submit" value="Submit" />
    </form>
  </body>
</html>

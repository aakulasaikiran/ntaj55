
<h1 style='color:red'> Registration Page</h1>

<form action="registerurl" method="POST">
  name: <input type="text" name="name"><br>
  age: <input type="text" name="age"><br>
  <input type="submit"  value="register"/>
  <input type="hidden" name="cToken" value="${sToken}"/>
</form>
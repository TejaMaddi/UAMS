<!DOCTYPE html>
<html>
<head>
    <title>Create Software</title>
</head>
<body>
<h2>Create Software</h2>
<form action="createSoftware" method="post">
    Software Name: <input type="text" name="name" required><br>
    Description: <textarea name="description" required></textarea><br>
    Access Levels: <select name="accessLevels">
        <option value="Read">Read</option>
        <option value="Write">Write</option>
        <option value="Admin">Admin</option>
    </select><br>
    <input type="submit" value="Create Software">
</form>
</body>
</html>

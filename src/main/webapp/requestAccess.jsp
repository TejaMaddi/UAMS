<!DOCTYPE html>
<html>
<head>
    <title>Request Access</title>
</head>
<body>
<h2>Request Access</h2>
<form action="requestAccess" method="post">
    Software ID: <input type="number" name="softwareId" required><br>
    Access Type: <select name="accessType" required>
        <option value="Read">Read</option>
        <option value="Write">Write</option>
        <option value="Admin">Admin</option>
    </select><br>
    Reason for Request: <textarea name="reason" required></textarea><br>
    <input type="submit" value="Request Access">
</form>
</body>
</html>

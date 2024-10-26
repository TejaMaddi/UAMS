<!DOCTYPE html>
<html>
<head>
    <title>Pending Requests</title>
</head>
<body>
<h2>Pending Requests</h2>
<!-- Fetch and display pending requests from the database -->
<form action="approveRequest" method="post">
    Request ID: <input type="number" name="requestId" required><br>
    Action: 
    <button type="submit" name="action" value="approve">Approve</button>
    <button type="submit" name="action" value="reject">Reject</button>
</form>
</body>
</html>

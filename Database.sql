-- Question 1 -- 
SELECT TenantName FROM Tenants 
INNER JOIN 
   (SELECT TenantID
	FROM AptTenants
	GROUP BY TenantID
	HAVING count(*) > 1) C
ON Tenants.TenantID = C.TenantID;

-- Question 2 --
SELECT BuildingName, ISNULL(Count, 0) as 'Count' 
FROM Buildings 
LEFT JOIN 
	(SELECT BuildingID, count(*) as 'Count'
	FROM Requests INNER JOIN Apartments
	ON Requests.AptID = Apartments.AptID
	WHERE Requests.Status = 'Open'
	GROUP BY Apartments.BuildingID) ReqCounts
ON Buildings.BuildingID = ReqCounts.BuildingID;

-- Question 3 --
UPDATE Requests 
SET Status = 'Closed'
WHERE AptID IN
	(SELECT AptID
	FROM Apartments 
	WHERE BuildingID = 11)
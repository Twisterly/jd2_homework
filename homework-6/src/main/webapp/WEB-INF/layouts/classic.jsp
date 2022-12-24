<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<title><tiles:getAsString name="title" /></title>
</head>

<body>
    <table width="100%">
        <tr>
            <td colspan="2">
                <tiles:insertAttribute name="header" />
            </td>
        </tr>
        <tr>
            <td width="20%" nowrap="nowrap" valign="top" bordercolor="black">
                 <tiles:insertAttribute name="menu" />
             </td>
            <td width="80%" valign="top">
                 <tiles:insertAttribute name="body" />
             </td>
        </tr>
        <tr>
            <td colspan="2">
                 <tiles:insertAttribute name="footer" />
            </td>
        </tr>
    </table>
</body>
</html>
public class Main {
  
  protected void logRequestParameters(HttpServletRequest request) {
    final String _METHOD = "[logRequestParameters]";
    Enumeration<String> parameterNames = request.getParameterNames();
    if (logger.isDebugEnabled()) {
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                logger.logDebug(_METHOD + "[paramValue][" + paramValue + "]");
            }
        }
    }
  }
}

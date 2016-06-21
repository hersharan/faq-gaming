var app = angular.module( 'app' );
app.factory( 'SessionInjector', [ '$q', 'localStorageService', 'Constants', '$rootScope',
  function( $q, localStorageService, Constants, $rootScope ) {
    var sessionInjector = {
      request: function( config ) {
        $rootScope.$broadcast( 'loadingStarted', {} );
        if ( localStorageService.get( Constants.AUTH_TOKEN_KEY ) ) {
          config.headers[ Constants.AUTH_TOKEN_KEY ] = localStorageService.get( Constants.AUTH_TOKEN_KEY );
        }
        return config;
      }
    };
    return sessionInjector;
  }
] );
app.factory( 'ResponseInterceptor', [ '$q', '$injector', '$rootScope',
  function( $q, $injector, $rootScope ) {
    return {
      response: function( response ) {
        $rootScope.$broadcast( 'loadingFinished', {} );
        // do something on success
        return response;
      },
      responseError: function( response ) {
        $rootScope.$broadcast( 'loadingFinished', {} );
        // do something on error
        var status = response.status;
        if ( status === 401 || status === 403 ) {
          // console.error( "It is  a bad request with code ", status );
          $injector.get( '$state' ).go( '/' );
        }
        return $q.reject( response );
      }
    };
  }
] );
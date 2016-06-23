angular.module( 'app', [ 'ui.router', 'ngMaterial', 'ngAnimate', 'ngMessages', 'ngAria', 'LocalStorageModule',
  'satellizer'
] ).config( function( $urlRouterProvider, $httpProvider, $locationProvider, $authProvider ) {
  $locationProvider.html5Mode( true );
  $urlRouterProvider.otherwise( '/' );
  $httpProvider.interceptors.push( 'SessionInjector' );
  $authProvider.baseUrl = Constants.APP_URL;
  $authProvider.facebook( {
    clientId: '1182289295136155'
  } );
  $authProvider.google( {
    clientId: '755756387603-uv8mg9ec1t8a7g0m7n22ju5m0vbud0p0.apps.googleusercontent.com'
  } );
} ).run( function() {
  console.log( 'Angular Started in App for Gamers' );
} );

angular.module( 'app', [ 'ui.router', 'ngMaterial', 'ngAnimate', 'ngMessages','ngAria','LocalStorageModule' ] ).config( function( $urlRouterProvider,
  $httpProvider, $locationProvider ) {
  $locationProvider.html5Mode( true );
  $urlRouterProvider.otherwise( '/' );
  $httpProvider.interceptors.push( 'SessionInjector' );
  $httpProvider.interceptors.push( 'ResponseInterceptor' );
} ).run( function() {
	console.log('Angular Started in App for Gamers');
} );

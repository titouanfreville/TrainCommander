module.exports = function(grunt) {

  grunt.loadNpmTasks('grunt-loopback-angular');

  grunt.initConfig({
    loopback_angular: {
      options: {
        input: 'api/server/server.js',
        output: 'js/lb-services.js'
      }
    }
  });

  grunt.registerTask('default', ['loopback_angular']);

};

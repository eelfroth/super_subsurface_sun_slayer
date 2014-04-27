attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;

uniform mat4 u_worldView;
varying vec4 v_color;
varying vec2 v_texCoords;

uniform float f_stauchfaktor;
uniform float f_ypos;
varying vec4 v_parable;
varying vec4 v_linear;
varying float v_ynorm;

uniform float f_width;
uniform float f_height;
varying vec4 v_resfactor;


void main() {
    v_color = vec4(1, 1, 1, 1);
    v_texCoords = a_texCoord0;

    v_parable = vec4(0, (a_position.x*a_position.x * f_stauchfaktor) + f_ypos, 0, 0);
    
    v_resfactor = vec4(f_width/f_height, f_height/f_width, 1, 1);
    
    v_ynorm = (a_position.y+3.0)/4.0;
    v_linear = vec4(v_ynorm, 1, 1, 1);
    //a_position.x = a_position.x/2 * (a_position.y*a_position.y)/(a_position.y);

    gl_Position = u_worldView * (a_position * v_linear - v_parable)  ;
}  

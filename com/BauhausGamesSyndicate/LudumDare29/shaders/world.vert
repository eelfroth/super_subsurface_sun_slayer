attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;

uniform mat4 u_worldView;
varying vec4 v_color;
varying vec2 v_texCoords;

varying vec4 v_parable;
uniform float f_stauchfaktor;
uniform float f_ypos;

void main() {
    v_color = vec4(1, 1, 1, 1);
    v_texCoords = a_texCoord0;

    v_parable = vec4(0, (a_position.x*a_position.x * f_stauchfaktor) + f_ypos, 0, 0);
    //a_position.y = v_parable;
    gl_Position = u_worldView * (a_position - v_parable);
}  

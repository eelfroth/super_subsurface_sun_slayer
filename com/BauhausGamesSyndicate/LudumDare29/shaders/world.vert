attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;

uniform mat4 u_worldView;
varying vec4 v_color;
varying vec2 v_texCoords;

uniform float factor;
varying vec4 displacement;


void main() {
    v_color = vec4(1, 1, 1, 1);
    v_texCoords = a_texCoord0;
    displacement = vec4(a_position.y, 1, 1, 1);
    gl_Position = u_worldView * a_position;
}  
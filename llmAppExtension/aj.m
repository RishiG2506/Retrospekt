% Define angular range for plotting orbits
angles = linspace(0, 2*pi, 100);  % Angle from 0 to 2*pi

% Geostationary Orbit (GEO)
geo_radius = 35786 * ones(size(angles));  % Constant radius for GEO in km

% Medium Earth Orbit (MEO)
meo_radius = linspace(2000, 20000, length(angles)); % Radius varies from 2000 km to 20000 km

% Low Earth Orbit (LEO)
leo_radius = linspace(160, 2000, length(angles)); % Radius varies from 160 km to 2000 km

% Plot GEO Orbit
figure;
polarplot(angles, geo_radius, 'b'); % GEO orbit in blue
hold on;

% Plot MEO Orbit
polarplot(angles, meo_radius, 'r'); % MEO orbit in red

% Plot LEO Orbit
polarplot(angles, leo_radius, 'g'); % LEO orbit in green

% Highlight specific GEO point
geo_radius_point = 35786;  % Example radius for GEO
geo_angle_point = pi/4;    % Example angle for GEO
polarplot(geo_angle_point, geo_radius_point, 'ko', 'MarkerFaceColor', 'k'); % Black marker for GEO

% Highlight specific MEO point
meo_radius_point = 10000;  % Example radius for MEO
meo_angle_point = pi/3;    % Example angle for MEO
polarplot(meo_angle_point, meo_radius_point, 'ko', 'MarkerFaceColor', 'k'); % Black marker for MEO

% Highlight specific LEO point
leo_radius_point = 1000;   % Example radius for LEO
leo_angle_point = pi/6;    % Example angle for LEO
polarplot(leo_angle_point, leo_radius_point, 'ko', 'MarkerFaceColor', 'k'); % Black marker for LEO

legend('GEO Orbit', 'MEO Orbit', 'LEO Orbit', 'Location', 'best');
title('Satellite Orbits Visualization');
hold off;

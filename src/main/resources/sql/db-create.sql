
CREATE TABLE IF NOT EXISTS SiteUser();
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS userId text;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS userName text;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS userEmail text;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS userFirstName text;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS userLastName text;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS userFullName text;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS seeArchived boolean;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS seeDeleted boolean;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS pk bigserial primary key;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS inheritPk text;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS created timestamp with time zone;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS archived boolean;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS sessionId text;
ALTER TABLE SiteUser ADD COLUMN IF NOT EXISTS userKey bigint;

CREATE TABLE IF NOT EXISTS CompanyWebinar();
ALTER TABLE CompanyWebinar ADD COLUMN IF NOT EXISTS name text;
ALTER TABLE CompanyWebinar ADD COLUMN IF NOT EXISTS description text;
ALTER TABLE CompanyWebinar ADD COLUMN IF NOT EXISTS pageId text;
ALTER TABLE CompanyWebinar ADD COLUMN IF NOT EXISTS resourceUri text;
ALTER TABLE CompanyWebinar ADD COLUMN IF NOT EXISTS templateUri text;
ALTER TABLE CompanyWebinar ADD COLUMN IF NOT EXISTS userUri text;
ALTER TABLE CompanyWebinar ADD COLUMN IF NOT EXISTS webinarUrlAmericas text;
ALTER TABLE CompanyWebinar ADD COLUMN IF NOT EXISTS webinarUrlApac text;
ALTER TABLE CompanyWebinar ADD COLUMN IF NOT EXISTS webinarUrlEmea text;
ALTER TABLE CompanyWebinar ADD COLUMN IF NOT EXISTS uri text;
ALTER TABLE CompanyWebinar ADD COLUMN IF NOT EXISTS url text;
ALTER TABLE CompanyWebinar ADD COLUMN IF NOT EXISTS pk bigserial primary key;
ALTER TABLE CompanyWebinar ADD COLUMN IF NOT EXISTS inheritPk text;
ALTER TABLE CompanyWebinar ADD COLUMN IF NOT EXISTS created timestamp with time zone;
ALTER TABLE CompanyWebinar ADD COLUMN IF NOT EXISTS archived boolean;
ALTER TABLE CompanyWebinar ADD COLUMN IF NOT EXISTS sessionId text;
ALTER TABLE CompanyWebinar ADD COLUMN IF NOT EXISTS userKey bigint;

CREATE TABLE IF NOT EXISTS IotService();
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS entityType text;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS apiKey text;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS cbHost text;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS resource text;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS attributes jsonb;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS service text;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS servicePath text;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS pageId text;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS resourceUri text;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS templateUri text;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS uri text;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS url text;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS title text;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS pk bigserial primary key;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS inheritPk text;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS created timestamp with time zone;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS archived boolean;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS sessionId text;
ALTER TABLE IotService ADD COLUMN IF NOT EXISTS userKey bigint;

CREATE TABLE IF NOT EXISTS WeatherObserved();
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS name text;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS description text;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS address jsonb;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS alternateName text;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS areaServed polygon;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS airQualityIndex decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS airQualityIndexForecast decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS aqiMajorPollutant text;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS aqiMajorPollutantForecast text;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS atmosphericPressure decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS dataProvider text;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS dateCreated text;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS dateModified text;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS dateObserved text;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS dewPoint decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS diffuseIrradiation decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS directIrradiation decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS gustSpeed decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS illuminance decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS location point;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS owner jsonb;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS precipitation decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS precipitationForecast decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS pressureTendency jsonb;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS refDevice jsonb;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS refPointOfInterest text;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS relativeHumidity decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS relativeHumidityForecast decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS seeAlso jsonb;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS snowHeight decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS solarRadiation decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS source text;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS streamGauge decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS temperature decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS airTemperatureForecast decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS airTemperatureTSA jsonb;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS feelsLikeTemperature decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS uVIndexMax decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS visibility jsonb;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS weatherType text;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS windDirection decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS windSpeed decimal;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS entityId text;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS ngsildTenant text;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS ngsildPath text;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS pk bigserial primary key;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS inheritPk text;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS created timestamp with time zone;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS archived boolean;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS sessionId text;
ALTER TABLE WeatherObserved ADD COLUMN IF NOT EXISTS userKey bigint;

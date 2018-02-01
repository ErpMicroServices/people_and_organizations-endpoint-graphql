import fs from "fs"

class Config {

  constructor () {
    this._currentEnvironment = process.env.NODE_ENV || defaultEnvironment()
    if (this._currentEnvironment == defaultEnvironment()) {
      this._config = {
        server: {
          cors: process.env.SERVER_CORS || {},
          endpoint: process.env.SERVER_ENDPOINT || '/',
          name: process.env.SERVER_NAME || "party-party-api",
          playground: process.env.SERVER_PLAYGROUND || '/',
          port: process.env.SERVER_PORT || 3000,
          subscriptions: process.env.SERVER_SUBSCRIPTIONS || '/',
          tracing: process.env.SERVER_TRACING || true,
          uploads: process.env.SERVER_UPLOADS || {maxFieldSize: 1024, maxFileSize: 1024, maxFiles: 1},
          version: process.env.SERVER_VERSION || "0.1.0",
          url: process.env.SERVER_URL || "http://localhost"
        },
        database: {
          host: process.env.DATABASE_HOST || 'localhost',
          port: process.env.DATABASE_PORT || 5432,
          database: process.env.DATABASE_DATABSE || 'party_database',
          user: process.env.DATABASE_USER || 'party_database',
          password: process.env.DATABASE_PASSWORD || 'party_database'
        },
        graphql: {
          graphiql: true,
          endpointURL: process.env.ENDPOINT_URL || '/api/party/graphql'
        }
      }
    } else {
      this._config = JSON.parse(fs.readFileSync(`config.${this.currentEnvironment}.json`, "utf8"))
    }
  }

  get currentEnvironment () {
    return this._currentEnvironment
  }

  get database () {
    return this._config.database
  }

  get graphql () {
    return this._config.graphql
  }

  get server () {
    return this._config.server
  }

}

const config = new Config()
export default config

export function defaultEnvironment () {
  return "default"
}

export function developmentEnvironment () {
  return "dev"
}

export function environments () {
  return [Config.defaultEnvironment(), Config.localEnvironment(), Config.developmentEnvironment(), Config.qaEnvironment(), Config.qaEnvironment(), Config.stagingEnvironment(), Config.prodEnvironment()]
}

export function localEnvironment () {
  return "local"
}

export function prodEnvironment () {
  return "prod"
}

export function qaEnvironment () {
  return "qa"
}

export function stagingEnvironment () {
  return "staging"
}

<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the
 * installation. You don't have to use the web site, you can
 * copy this file to "wp-config.php" and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * MySQL settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://codex.wordpress.org/Editing_wp-config.php
 *
 * @package WordPress
 */

// ** MySQL settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define( 'DB_NAME', 'local' );

/** MySQL database username */
define( 'DB_USER', 'root' );

/** MySQL database password */
define( 'DB_PASSWORD', 'root' );

/** MySQL hostname */
define( 'DB_HOST', 'localhost' );

/** Database Charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8' );

/** The Database Collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**
 * Authentication Unique Keys and Salts.
 *
 * Change these to different unique phrases!
 * You can generate these using the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}
 * You can change these at any point in time to invalidate all existing cookies. This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define('AUTH_KEY',         '9Ecb9Hq70JQTTXL++uj5a6X4zaHG7XyRypmpVuMKsTPn3QivRxyvwSVPdnzMGSzXQeTYNZ7Tp4oij+2OAP6cNA==');
define('SECURE_AUTH_KEY',  'gA1FJZgU0jb1rS7eFxvAsaabnaTcz6Jkg31jFJlyOj5aKrv+4kQ4jybrIxqEg1UO27lAtA5lmskKapWqbd9E8Q==');
define('LOGGED_IN_KEY',    'h4uCs61KgNz3ZwVN7Jtn87mq3AcnmK6j/tZeIqf75thuAES4RxC726UKvqbwbqC+k3BDeVYABsZNkpcKwSpW6A==');
define('NONCE_KEY',        'l4eR8rDe4VhqRkRhTbuSQEcmOpFIXK2PUjOFlUnU/DRs1oj/zWe75loAkGZbffHYvI0p1ipqLRKM4dJQlpI+SQ==');
define('AUTH_SALT',        'HWSTjMRlL4smHv05VatDG7Hvp5TUSX2URJInxK5ak6JSvbulP1xuf+k1XLH6btqjIWkOneQP5i42XhEOtGNOxA==');
define('SECURE_AUTH_SALT', 'erTk9rq8rVTogxgWlfaJfChcnHMgH/e42yLqGtwT67y2+g3eK0iGKJ0pY6apMLRrXjKFdHVfsMJjhVWLcwbQgw==');
define('LOGGED_IN_SALT',   'QWzYlk61k9FOhD60dRnSesQ8L/LpJTrRg0x/Wr8ZEC47CrI+lqe/CC6CeNYchSQd1F7KNOgMCickjzl8Z7cgvw==');
define('NONCE_SALT',       '1L2fdLvVAdRtJ8vqGsMT9be9nDbcNuTjzFluXQNN7WqM0SE8lHI9Jh67FI9haQNxYYq5WoEoZjA59KxVhO0n0Q==');

/**
 * WordPress Database Table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'wp_';




/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', dirname( __FILE__ ) . '/' );
}

/** Sets up WordPress vars and included files. */
require_once ABSPATH . 'wp-settings.php';

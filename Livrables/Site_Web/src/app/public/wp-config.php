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
define('AUTH_KEY',         'znhD3ppvyt5sLeH4UlQLM6bsN5WVD+VKYa4AzEzZ5UtzdCYge5Zct/0olCqyl/Ip3nYhYBh6mvwDibKn0B02+g==');
define('SECURE_AUTH_KEY',  'R0eR/voeU9IuO35VSCgGrFsTzqS9TsDHzgkNK9xmt0ivW4gdnGhdJ0RgBnUNbniT2hp7lqxQyr861hjVBYpj4A==');
define('LOGGED_IN_KEY',    'zs3yyiruZl8Iw5yQnk4YAKFznrAMR8vJgIQMtB/jf+DOAXd2NVCwRuSxyPxeHn3i3ZtFQUN/x7iVrhwzsCWdPg==');
define('NONCE_KEY',        'jZ2glF76OiIpQHOaUR9vYeEmXCJ9JtBopFmQQtwvrkNfqlU9lEDuQhfRlUqeMRWD6FpkGVjgbjjMrh0VN7uVgg==');
define('AUTH_SALT',        '4K3EdXeRL52tQk4q9ISK3lQRDUqyhl0Hg2pwdsdIvt3N3M/RkL6BW0n+3Mwve+HKLBQNUg+jVUz6XIIkTS8ltg==');
define('SECURE_AUTH_SALT', 'Z4J3El0MM7+DWNq1hzMP0lKS5g8uhXTXbSIAxu3fepqYVRgIxSSzil9iqOcD5Mz8NCLxkNganhRU9A4vlLSSFQ==');
define('LOGGED_IN_SALT',   '7PNmnw+VfU+8U+CSW+K4KkgLvo9YFtBLS8d14az9cLxbX57ndk9uFRAPswwtQmVVcOjsNGoXB2ECdqus+U01hg==');
define('NONCE_SALT',       '73yXHmZmOXL2HipImVd7LBoP+veVMiPjNLLw/UiBpu6SIvqLXE/aSCD/NNdGJ+lbQPum1pZLO/EXB5d4j2lH9g==');

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
